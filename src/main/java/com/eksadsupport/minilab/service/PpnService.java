package com.eksadsupport.minilab.service;

import com.eksadsupport.minilab.domain.Ppn;
import com.eksadsupport.minilab.dto.ppn.GetPpn;
import com.eksadsupport.minilab.dto.ppn.PpnList;
import com.eksadsupport.minilab.repository.PpnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PpnService {
    @Autowired
    PpnRepository ppnRepository;

    public GetPpn savePpn(String ppnId, String dealerId, String ppnDescription, Float ppnRate, Float ppnRatePrevious,
                          Date effectiveStartDate, Date effectiveEndDate, String ppnStatus){

        ppnRepository.savePpn(ppnId, dealerId, ppnDescription, ppnRate, ppnRatePrevious,
                effectiveStartDate, effectiveEndDate, ppnStatus);

        Ppn ppn =ppnRepository.getByPpnId(ppnId);

        return new GetPpn(ppn.getPpnId(), ppn.getDealer().getDealerId(), ppn.getDescription(), ppn.getPpnRate(),
                ppn.getPpnRatePrevious(), ppn.getEffectiveStartDate(), ppn.getEffectiveEndDate(), ppn.getPpnStatus());
    }

    public GetPpn updatePpn(String ppnId, String dealerId, String ppnDescription, Float ppnRate, Float ppnRatePrevious,
                            Date effectiveStartDate,Date effectiveEndDate, String ppnStatus){
        ppnRepository.updatePpn(ppnId, dealerId, ppnDescription, ppnRate, ppnRatePrevious,
                effectiveStartDate, effectiveEndDate, ppnStatus);
        Ppn ppn =ppnRepository.getByPpnId(ppnId);

        return new GetPpn(ppn.getPpnId(), ppn.getDealer().getDealerId(), ppn.getDescription(), ppn.getPpnRate(),
                ppn.getPpnRatePrevious(), ppn.getEffectiveStartDate(), ppn.getEffectiveEndDate(), ppn.getPpnStatus());
    }

    public List<PpnList> listAll(String dealerId, String ppnStatus, int limit, int offset) {
        List<PpnList> listAll = new ArrayList<>();
        List<Ppn> opt = ppnRepository.listAll(dealerId, ppnStatus, limit, offset);

        for (int i = 0; i < opt.size(); i++) {
            PpnList ppnList = new PpnList();
            ppnList.setPpnId(opt.get(i).getPpnId());
            ppnList.setDealerId(opt.get(i).getDealer().getDealerId());
            ppnList.setPpnDescription(opt.get(i).getDescription());
            ppnList.setPpnRate(opt.get(i).getPpnRate());
            ppnList.setPpnRatePrevious(opt.get(i).getPpnRatePrevious());
            ppnList.setEffectiveStartDate(opt.get(i).getEffectiveStartDate());
            ppnList.setEffectiveEndDate(opt.get(i).getEffectiveEndDate());
            ppnList.setPpnStatus(opt.get(i).getPpnStatus());
            listAll.add(ppnList);
        }
        return listAll;
    }

    public PpnList getPpnById(String ppnId) {
        PpnList ppnById = new PpnList();
        Optional<Ppn> opt = ppnRepository.findByPpnId(ppnId);

        ppnById.setPpnId(opt.get().getPpnId());
        ppnById.setDealerId(opt.get().getDealer().getDealerId());
        ppnById.setPpnDescription(opt.get().getDescription());
        ppnById.setPpnRate(opt.get().getPpnRate());
        ppnById.setPpnRatePrevious(opt.get().getPpnRatePrevious());
        ppnById.setEffectiveStartDate(opt.get().getEffectiveStartDate());
        ppnById.setEffectiveEndDate(opt.get().getEffectiveEndDate());
        ppnById.setPpnStatus(opt.get().getPpnStatus());

        return ppnById;
    }

    public Optional<Ppn> findByPpnId(String ppnId){
        return ppnRepository.findByPpnId(ppnId);
    }

}
