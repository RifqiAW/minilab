package com.eksadsupport.minilab.Controller;

import com.eksadsupport.minilab.domain.Ppn;
import com.eksadsupport.minilab.dto.ppn.GetActivePpn;
import com.eksadsupport.minilab.dto.ppn.GetPpn;
import com.eksadsupport.minilab.dto.ppn.PpnList;
import com.eksadsupport.minilab.dto.response.Response;
import com.eksadsupport.minilab.dto.response.ResponseBadRequest;
import com.eksadsupport.minilab.dto.response.ResponseNoContent;
import com.eksadsupport.minilab.dto.response.ResponseSuccess;
import com.eksadsupport.minilab.service.PpnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.eksadsupport.minilab.Common.Util.*;
import static com.eksadsupport.minilab.Common.Util.valueToStringOrEmpty;

@RestController
@RequestMapping("/ddms/v1/cmd/master/ppn")
public class PpnCmdController {
    @Autowired
    PpnService ppnService;

    @PostMapping("save")
    public ResponseEntity<Object> savePpn(
            @RequestBody final Map<String, Object> request
    ) throws ParseException {
        try {
            String ppnId = request.get("id").toString();
            String dealerId = request.get("dealerId").toString();
            String ppnDescription = request.get("ppnDescription").toString();
            Float ppnRate = Float.parseFloat(request.get("ppnRate").toString());
            Float ppnRatePrevious = Float.parseFloat(request.get("ppnRatePrevious").toString());
            String startDate = (request.get("effectiveStartDate").toString());
            String endDate = request.get("effectiveEndDate").toString();
            String ppnStatus = request.get("status").toString().toUpperCase(Locale.ROOT);

//            Locale locale = new Locale("id", "id");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            Date effectiveStartDate = sdf.parse(startDate);
            Date effectiveEndDate = sdf.parse(endDate);

            if(checkStringIfNulllOrEmpty(ppnId) && checkStringIfNulllOrEmpty(ppnDescription) || checkStringIfNulllOrEmpty(dealerId)
                    || checkStringIfNulllOrEmpty(String.valueOf(ppnRate))
                    || checkStringIfNulllOrEmpty(String.valueOf(effectiveStartDate)) || checkStringIfNulllOrEmpty(ppnStatus)){
                return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
            }
            //Insert DATA
            if(checkStringIfNulllOrEmpty(ppnId)){
                GetPpn ppn = ppnService.savePpn(generateId(), dealerId, ppnDescription, ppnRate, ppnRatePrevious, effectiveStartDate, effectiveEndDate, ppnStatus);
                return new ResponseEntity<>(new ResponseSuccess(ppn), HttpStatus.OK);
            }

            //Update DATA
            String cekId = ppnService.cekPpnId(ppnId);

            if (cekId.equals(ppnId)){

                if(checkStringIfNulllOrEmpty(dealerId)){
                    String dealer_id = ppnService.cekDealerId(ppnId);
                    dealerId = dealerId;
                }
                if(checkStringIfNulllOrEmpty(ppnDescription)){
                    String description = ppnService.cekPpnDescription(ppnId);
                    ppnDescription = description;
                }
                if(checkStringIfNulllOrEmpty(String.valueOf(ppnRate))){
                    String rate = ppnService.cekPpnRate(ppnId);
                    ppnRate = Float.valueOf(rate);
                }
                try {
                    if(checkStringIfNulllOrEmpty(String.valueOf(ppnRatePrevious))){
                        String rateprev = ppnService.cekPpnRate(ppnId);
                        ppnRatePrevious = Float.valueOf(rateprev);
                    }
                }catch (Exception e){
                    ppnRatePrevious = null;
                }
                if(checkStringIfNulllOrEmpty(String.valueOf(effectiveStartDate))){
                    Date effstartDate = ppnService.cekEffectiveStartDate(ppnId);
                    effectiveStartDate = effstartDate;
                }
                try {
                    if(checkStringIfNulllOrEmpty(String.valueOf(effectiveEndDate))){
                        Date effEndtDate = ppnService.cekEffectiveEndDate(ppnId);
                        effEndtDate = effEndtDate;
                    }
                }catch (Exception e){
                    effectiveEndDate = null;
                }
                if(checkStringIfNulllOrEmpty(ppnStatus)){
                    String ppnStat = ppnService.cekPpnStatus(ppnId);
                    ppnStatus = ppnStat;
                }
                GetPpn ppn = ppnService.updatePpn(ppnId, dealerId, ppnDescription, ppnRate, ppnRatePrevious,
                        effectiveStartDate, effectiveEndDate, ppnStatus);
                return new ResponseEntity<>(new ResponseSuccess(ppn), HttpStatus.OK);
            }

        }catch (NullPointerException ne){
            ne.printStackTrace();
            return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new  ResponseBadRequest(), HttpStatus.BAD_REQUEST);
    }
}
