package com.wander.sqa.service.group;

import com.wander.sqa.dao.RegistrationDAO;
import com.wander.sqa.dao.TermDAO;
import com.wander.sqa.dto.group.TermDTO;
import com.wander.sqa.entity.group.Term;
import com.wander.sqa.entity.registration.Registration;
import com.wander.sqa.exception.RegTimeException;
import com.wander.sqa.exception.TermNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TermServiceImpl implements TermService{
    @Autowired
    private TermDAO termDAO;
    @Autowired
    private RegistrationDAO registrationDAO;

    @Override
    public TermDTO getLastTerm() throws TermNotFoundException {
        Term term = this.termDAO.getLastTerm().orElseThrow(TermNotFoundException::new);
        TermDTO termDTO = new TermDTO();
        termDTO.setEndDate(term.getEndDate());
        termDTO.setEndRegTime(term.getEndRegTime());
        termDTO.setStartDate(term.getStartDate());
        termDTO.setStartRegTime(term.getStartRegTime());
        return termDTO;
    }

    @Override
    @Transactional
    public void updateTerm(TermDTO termDTO) throws TermNotFoundException, RegTimeException {
        Term term = this.termDAO.getLastTerm()
                .orElseThrow(TermNotFoundException::new);
        //Nếu thời gian kết thúc đăng kí nhỏ hơn thời gian bắt đầu thì báo lỗi
        if(termDTO.getStartRegTime().after(termDTO.getEndRegTime())){
            throw new RegTimeException("Thời gian bắt đầu đăng kí phải nhỏ hơn thời gian kết thúc đăng kí!");
        }
        //Nếu thời gian kết thúc đăng kí lớn hơn thời gian bắt đầu kì thì báo lỗi
        if(termDTO.getEndRegTime().after(term.getStartDate())){
            throw new RegTimeException("Thời gian kết thúc đăng kí phải nhỏ hơn thời gian bắt đầu kì mới");
        }
        //Cập nhật kì mới
        term.setStartRegTime(termDTO.getStartRegTime());
        term.setEndRegTime(termDTO.getEndRegTime());
        this.termDAO.save(term);
        //Vô hiệu hóa tất cả đăng kí trước đó
        List<Registration> registrationList= this.registrationDAO
                .findAllEnableByTerm(term.getId());
        this.registrationDAO.saveAll(registrationList
                .stream().map(item->{
                    item.setEnable(false);
                    return item;
                }).collect(Collectors.toList()));
    }
}
