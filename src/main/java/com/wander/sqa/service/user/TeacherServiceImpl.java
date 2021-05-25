package com.wander.sqa.service.user;

import com.wander.sqa.dao.TeacherDAO;
import com.wander.sqa.dto.user.SubjectTeacherStatDTO;
import com.wander.sqa.dto.user.TeacherDTO;
import com.wander.sqa.dto.user.TeacherStatDTO;
import com.wander.sqa.entity.user.Fullname;
import com.wander.sqa.entity.user.Teacher;
import com.wander.sqa.exception.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	private TeacherDAO teacherDAO;

	//Tìm kiếm thông tin theo tài khoản
	@Override
	@Transactional
	public TeacherDTO findByUsername(String u) throws UsernameNotFoundException {
		Teacher teacher = this.teacherDAO.findByUsername(u)
				.orElseThrow(UsernameNotFoundException::new);
		TeacherDTO teacherDTO = new TeacherDTO();
		teacherDTO.setId(teacher.getId());
		return teacherDTO;
	}

	private List<SubjectTeacherStatDTO> getAns(List<Teacher> teacherList){
		return teacherList.stream()
				.map(item->{
					SubjectTeacherStatDTO ans= new SubjectTeacherStatDTO();
					ans.setId(item.getId());
					ans.setCode(item.getTchCode());
					ans.setFullname(this.getFullname(item.getFullname()));
					return ans;
				}).collect(Collectors.toList());
	}

	//Tìm kiếm giảng viên đăng kí đủ
	@Override
	@Transactional
	public List<SubjectTeacherStatDTO> findRemember(long id) {
		List<Teacher> teacherList = this.teacherDAO.findRemember(id);
		return this.getAns(teacherList);
	}

	//Tìm kiếm giảng viên đăng kí thiếu
	@Override
	@Transactional
	public List<SubjectTeacherStatDTO> findForgot(long id) {
		List<Teacher> teacherList = this.teacherDAO.findForgot(id);
		return this.getAns(teacherList);
	}

	@Override
	@Transactional
	public List<TeacherStatDTO> getAll() {
		//Lấy danh sách giảng viên được giao dạy cùng với số môn được giao
		List<Object[]> groupTeacher = this.teacherDAO.findTeacherWithNumberOfGroup();
		//Nếu danh sách rỗng thì tức là chưa giao cho ai
		if(groupTeacher == null||groupTeacher.isEmpty()){
			return new ArrayList<>();
		}
		//Lấy danh sách giảng viên đã giao
		List<TeacherStatDTO> ans = this.getAssignedTeacher(groupTeacher);
		//Lấy danh sách đăng kí đủ theo giảng viên
		List<Object[]> fullRegTeacher = this.teacherDAO.findTeacherWithNumberOfFullRegGroup();
		//Nếu không ai đăng kí đủ thì trả về luôn
		if(fullRegTeacher == null||fullRegTeacher.isEmpty()){
			return ans;
		}
		//Tạo map lưu lại
		Map<Long,Long> fullRegTeacherMap = this.getMap(fullRegTeacher);
		//Cài đặt số môn đăng kí đủ
		ans.forEach(item->{
			Long count = fullRegTeacherMap.get(item.getId());
			//Nếu không rỗng thì đã đăng kí đủ
			count = count==null?0:count;
			item.setRemember(count);
			item.setForgot(item.getForgot()-count);
		});
		return ans;
	}

	//Chuyển về dto
	private List<TeacherStatDTO> getAssignedTeacher(List<Object[]> groupTeacher) {
		List<TeacherStatDTO> ans = new ArrayList<>();
		try{
			groupTeacher.forEach(item->{
				Teacher teacher = (Teacher) item[0];
				Long count = (Long) item[1];
				TeacherStatDTO teacherStatDTO = new TeacherStatDTO();
				teacherStatDTO.setId(teacher.getId());
				teacherStatDTO.setForgot(count);
				teacherStatDTO.setRemember(0);
				teacherStatDTO.setFullname(this.getFullname(teacher.getFullname()));
				ans.add(teacherStatDTO);
			});
			return ans;
		}catch (Exception e){
			e.printStackTrace();
			return ans;
		}
	}

	//Lấy họ và tên
	private String getFullname(Fullname fullname){
		if(fullname.getMiddlename()==null) fullname.setMiddlename("");
		return fullname.getFirstname()+" "+fullname.getMiddlename()+" "+fullname.getLastname();
	}

	//Lấy Map đến theo giảng viên
	private Map<Long,Long> getMap(List<Object[]> list){
		Map<Long,Long> map = new HashMap<>();
		try{
			for(Object[] o:list){
				Teacher teacher = (Teacher) o[0];
				Long count = (Long) o[1];
				map.put(teacher.getId(),count);
			}
			return map;
		}catch (Exception e){
			e.printStackTrace();
			return map;
		}
	}
}