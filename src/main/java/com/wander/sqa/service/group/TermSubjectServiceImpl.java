package com.wander.sqa.service.group;

import com.wander.sqa.dao.TermSubjectDAO;
import com.wander.sqa.dto.group.TermSubjectStatDTO;
import com.wander.sqa.entity.group.TermSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TermSubjectServiceImpl implements TermSubjectService{
    @Autowired
    private TermSubjectDAO termSubjectDAO;

    @Override
    public List<TermSubjectStatDTO> getAll() {
        //Lấy danh sách môn học đã giao cùng với số lượng giảng viên được giao
        List<Object[]> teacherAssignedSubject = this.termSubjectDAO
                .getTermSubjectWithTeacherCount();
        //Nếu danh sách rỗng thì tức là chưa giao môn nào
        if(teacherAssignedSubject == null||teacherAssignedSubject.isEmpty()){
            return new ArrayList<>();
        }
        //Tạo ra danh sách môn
        List<TermSubjectStatDTO> ans = this.getAns(teacherAssignedSubject);
        //Lấy danh sách môn học đã giao cùng với số lượng các giảng viên đã đăng kí đủ
        List<Object[]> regAssignedSubject = this.termSubjectDAO.getTermSubjectWithRegCount();
        //Nếu danh sách rỗng thì tức là thiếu hết
        if(regAssignedSubject == null || regAssignedSubject.isEmpty()){
            return ans;
        }
        //Tạo map để lưu trữ
        Map<Long,Long> regAssignedSubjectMap = this.getMap(regAssignedSubject);
        //Đếm số giảng viên đã đăng kí đủ , chưa đăng kí đủ
        ans.forEach(item->{
            Long count = regAssignedSubjectMap.get(item.getId());
            //Nếu không tồn tại tức là chưa có ai đăng kí đủ
            count = count==null?0:count;
            item.setRemember(count);
            item.setForgot(item.getForgot()-count);
        });
        return ans;
    }

    //Chuyển đổi thành map
    private Map<Long, Long> getMap(List<Object[]> regAssignedSubject) {
        Map<Long,Long> ans = new HashMap<>();
        try{
            regAssignedSubject.forEach(item->{
                TermSubject termSubject = (TermSubject) item[0];
                Long count = (Long) item[1];
                ans.put(termSubject.getId(),count);
            });
            return ans;
        }catch (Exception e){
            e.printStackTrace();
            return ans;
        }
    }

    //Chuyển dạng sang dto
    private List<TermSubjectStatDTO> getAns(List<Object[]> teacherAssignedSubject) {
        List<TermSubjectStatDTO> ans = new ArrayList<>();
        try{
            teacherAssignedSubject.forEach(item->{
                TermSubject termSubject= (TermSubject) item[0];
                Long count  = (Long) item[1];
                TermSubjectStatDTO tmp = new TermSubjectStatDTO();
                tmp.setId(termSubject.getId());
                tmp.setTermSubjectName(termSubject.getSubject().getName());
                tmp.setForgot(count);
                tmp.setRemember(0);
                ans.add(tmp);
            });
            return ans;
        }catch (Exception e){
            e.printStackTrace();
            return ans;
        }
    }
}
