package com.eksadsupport.minilab.Controller;

import com.eksadsupport.minilab.domain.Ppn;
import com.eksadsupport.minilab.dto.ppn.GetActivePpn;
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

import static com.eksadsupport.minilab.Common.Util.generateQueryDate;
import static com.eksadsupport.minilab.Common.Util.valueToStringOrEmpty;

@RestController
@RequestMapping("/ddms/v1/qry/master/ppn")
public class PpnQryController {
    @Autowired
    PpnService ppnService;

    @Value("${property.max_limit}")
    public int CONSTANTS_MAX_LIMIT;

    @PostMapping("listByDealerId")
    public ResponseEntity<Object> listByDealerId(
            @RequestBody Map<String, Object> request
    ){
        String dealerId = valueToStringOrEmpty(request, "dealerId");
        String ppnStatus = valueToStringOrEmpty(request, "ppnStatus".toUpperCase(Locale.ROOT));
        String offset_s = valueToStringOrEmpty(request, "offset");
        String limit_s = valueToStringOrEmpty(request, "limit");

        int offset = 0;
        int limit = CONSTANTS_MAX_LIMIT;


        if(dealerId.isEmpty() && ppnStatus.isEmpty()){
            return new ResponseEntity(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
        }

        if(!limit_s.isEmpty() || limit_s == null){
            limit = Integer.parseInt(limit_s);
        }
        if(!offset_s.isEmpty() || offset_s == null){
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
        Optional<Ppn> opt = ppnService.findByPpnId(ppnId);
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
        Date queryDates;

        if (queryDate.equalsIgnoreCase("") || queryDate == null){

            Map<String, Object> map = new LinkedHashMap<>();
            List<PpnList> data = ppnService.listAllByDealer(dealerId);
            map.put("QueryDate", generateQueryDate());
            map.put("data",ppnService.listAllByDealer(dealerId));
            return new ResponseEntity<>(map, HttpStatus.OK);
        }else {
            queryDates = sdf.parse(queryDate);
            Optional<Ppn> cek = ppnService.findActivePpn(dealerId, queryDates);

            if (!cek.isPresent()){
                String res = "Data Tidak Valid";
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }else {
                Optional<Ppn> opt = ppnService.findActivePpn(dealerId, queryDates);

                GetActivePpn getActivePpn = new GetActivePpn();
                getActivePpn.setId(opt.get().getPpnId());
                getActivePpn.setDealerId(opt.get().getDealer().getDealerId());
                getActivePpn.setPpnDescription(opt.get().getDescription());
                getActivePpn.setPpnRate(opt.get().getPpnRate());
                getActivePpn.setPpnRatePrevious(opt.get().getPpnRatePrevious());
                getActivePpn.setEffectiveStartDate(opt.get().getEffectiveStartDate());
                getActivePpn.setEffectiveEndDate(opt.get().getEffectiveEndDate());
                getActivePpn.setStatus(opt.get().getPpnStatus());

                Response response = new Response();
                response.setStatus("S");
                response.setCode("201");
                response.setMessage("Processed Successed");
                response.setData(getActivePpn);

                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
    }
}
