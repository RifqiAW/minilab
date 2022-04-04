package com.eksadsupport.minilab.Controller;

import com.eksadsupport.minilab.domain.Ppn;
import com.eksadsupport.minilab.dto.ppn.GetPpn;
import com.eksadsupport.minilab.dto.ppn.PpnList;
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
public class PpnController {
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

    @Value("${property.max_limit}")
    public int CONSTANTS_MAX_LIMIT;

    @PostMapping("listByDealerId")
    public ResponseEntity<Object> listByDealerId(
            @RequestBody Map<String, Object> request
    ){
        String dealerId = valueToStringOrEmpty(request, "dealerId");
        String ppnStatus = valueToStringOrEmpty(request, "ppnStatus");
        String offset_s = valueToStringOrEmpty(request, "offset");
        String limit_s = valueToStringOrEmpty(request, "limit");

        int offset = 0;
        int limit = CONSTANTS_MAX_LIMIT;


        if(dealerId.isEmpty() && ppnStatus.isEmpty()){
            return new ResponseEntity(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
        }

        if(!limit_s.isEmpty()){
            limit = Integer.parseInt(limit_s);
        }
        if(!offset_s.isEmpty()){
            offset = Integer.parseInt(offset_s);
        }

        Map<String, Object> map = new LinkedHashMap<>();
        List<PpnList> index = ppnService.listAll(dealerId,ppnStatus,limit,offset);
        map.put("status","S");
        map.put("code","201");
        map.put("message","Process Success");
        map.put("data",ppnService.listAll(dealerId,ppnStatus,limit,offset));
        map.put("dataOfRecord",index.size());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("get/{ppnId}")
    public ResponseEntity<Object> getById(
            @PathVariable String ppnId
    ){
        Optional<Ppn>opt = ppnService.findByPpnId(ppnId);
        if(ppnId.isEmpty()){
            return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
        }
        try {
            PpnList ppnList= ppnService.getPpnById(ppnId);
            return new ResponseEntity<>(new ResponseSuccess(ppnList), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseNoContent(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("getActivePpn")
    public ResponseEntity<Object> getActivePpn(
            @RequestBody Map<String, Object> request
    ) throws ParseException {
        String dealerId = request.get("dealerId").toString();
        String queryDate = request.get("queryDate").toString();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Date querydates = sdf.parse(queryDate);

        String ppid = ppnService.cekPPnIdBydealer(dealerId);
        PpnList opt = ppnService.getActivePpn(dealerId, querydates);

        System.out.println(dealerId);
        System.out.println(opt);

        if (dealerId.isEmpty()){
            return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
        }

        try {
            if (queryDate.isEmpty()){
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("queryDate", generateQueryDate());
                map.put("data", ppnService.listAllByDealer(dealerId));

                return new ResponseEntity<>(map, HttpStatus.OK);
            }else {
                PpnList ppnList= ppnService.getActivePpn(dealerId, querydates);
                return new ResponseEntity<>(new ResponseSuccess(ppnList), HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseNoContent(), HttpStatus.NO_CONTENT);
        }
    }
}
