package com.wander.sqa.service.group;

import com.wander.sqa.dao.SubjectGroupDAO;
import com.wander.sqa.dao.TermWeekDAO;
import com.wander.sqa.dto.group.GroupInfoDTO;
import com.wander.sqa.dto.group.SubjectGroupDTO;
import com.wander.sqa.dto.group.TermSubjectDTO;
import com.wander.sqa.entity.group.GroupInfo;
import com.wander.sqa.entity.group.LearningWeek;
import com.wander.sqa.entity.group.SubjectGroup;
import com.wander.sqa.entity.group.TermWeek;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SubjectGroupServiceImpl implements SubjectGroupService {
	@Autowired
	private SubjectGroupDAO subjectGroupDAO;
	@Autowired
	private TermWeekDAO termWeekDAO;

	@Override
	@Transactional
	public List<SubjectGroupDTO> findByTermSubject(TermSubjectDTO t) {
		//Lấy tất cả group cho môn học lên
		List<SubjectGroup> subjectGroupList = this.subjectGroupDAO.findByTermSubject(t.getId());
		//Tìm tất cả tuần học cho kì cuối
		List<TermWeek> termWeekList  = this.termWeekDAO.findAllTermWeekByLastTerm();
		//Sắp xếp lại tuần học theo ngày bắt đầu
		Collections.sort(termWeekList,(x,y)->x.getStartDate().compareTo(y.getStartDate()));
		//Tạo map lưu tuần học theo số thứ tự
		Map<Long,Integer> termWeekMap = new TreeMap<>();
		for(int i=0;i<termWeekList.size();i++){
			termWeekMap.put(termWeekList.get(i).getId(),i);
		}
		//Chuyển đổi theo định dạng dto
		return subjectGroupList.stream().map(item->{
			SubjectGroupDTO subjectGroupDTO = new SubjectGroupDTO();
			subjectGroupDTO.setId(item.getId());
			subjectGroupDTO.setLearningDay(item.getLearningDay());
			subjectGroupDTO.setNumberOfTeacher(item.getNumberOfTeacher());
			subjectGroupDTO.setCode(item.getCode());
			List<GroupInfoDTO> groupInfoDTOList = new ArrayList<>();
			//Chuyển đổi lịch học về dạng mong muốn
			for(GroupInfo groupInfo:item.getGroupInfo()) {
				GroupInfoDTO groupInfoDTO = new GroupInfoDTO();
				//Tên phòng học theo định dạng : tên phòng - tên tòa nhà
				groupInfoDTO.setRoom(groupInfo.getRoom().getName() + "-"
						+ groupInfo.getRoom().getBuilding().getName());
				groupInfoDTO.setShift(groupInfo.getShift().getName());
				//thực hiện chuyển các tuần học về định dạng chỉ lấy chữ số cuối
				int[] weekList = new int[termWeekList.size()];
				for (LearningWeek learningWeek : groupInfo.getLearningWeek()) {
					//Nếu nhóm được nghỉ tuần đấy thì không lấy
					if(!learningWeek.isDesist()) {
						Integer tmp = termWeekMap.get(learningWeek.getTermWeek().getId());
						if(tmp !=null) weekList[tmp]=1;
					}
				}
				//Chuyển đổi tuần học về định dạng mong muốn
				String s ="";
				for(int i= 0 ;i<weekList.length;i++){
					if(weekList[i]==1){
						s+=((i+1)%10);
					}else {
						s += "_";
					}
				}
				groupInfoDTO.setLearningWeek(s);
				groupInfoDTOList.add(groupInfoDTO);
			}
			subjectGroupDTO.setGroupInfo(groupInfoDTOList);
			return subjectGroupDTO;
		}).collect(Collectors.toList());
	}
}