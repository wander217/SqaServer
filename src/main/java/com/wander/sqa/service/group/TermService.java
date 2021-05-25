package com.wander.sqa.service.group;

import com.wander.sqa.dto.group.TermDTO;
import com.wander.sqa.exception.RegTimeException;
import com.wander.sqa.exception.TermNotFoundException;

public interface TermService {
    TermDTO getLastTerm() throws TermNotFoundException;
    void updateTerm(TermDTO termDTO) throws TermNotFoundException, RegTimeException;
}
