package com.eksadsupport.minilab.service;


import com.eksadsupport.minilab.domain.Customer;
import com.eksadsupport.minilab.domain.Sales;
import com.eksadsupport.minilab.dto.customer.GetCustomerById;
import com.eksadsupport.minilab.dto.customer.GetCustomerDTO;
import com.eksadsupport.minilab.dto.customer.ListAllDTO;
import com.eksadsupport.minilab.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository cr;


    public int addCus(String customerId, String customerName, String dealerId, String customerGender,
                      String customerNik, String customerKk, String customerEmail, String customerAddress,
                      String customerTelp, String customerHp,
                      String customerStatus, String salesId) {
        return cr.addCustomer(customerId, customerName, dealerId, customerGender, customerNik, customerKk,
                customerEmail, customerAddress, customerTelp, customerHp, customerStatus, salesId);
    }

    public int updateCus(String customerName, String dealerId, String customerGender,
                         String customerNik, String customerKk, String customerEmail, String customerAddress,
                         String customerTelp, String customerHp, String customerStatus, String salesId, String customerId) {
        return cr.updateCustomer(customerName, dealerId, customerGender, customerNik, customerKk, customerEmail, customerAddress,
                customerTelp, customerHp, customerStatus, salesId, customerId);
    }


    public GetCustomerDTO getCustomerDTO(String customerId) {
        GetCustomerDTO getCustomerDTO = new GetCustomerDTO();
        Optional<Customer> opt = cr.getCustomerById(customerId);

        getCustomerDTO.setCustomerId(opt.get().getCustomerId());
        getCustomerDTO.setCustomerNama(opt.get().getCustomerName());
        getCustomerDTO.setDealerId(opt.get().getDealer().getDealerId());
        getCustomerDTO.setCustomerGender(opt.get().getCustomerGender());
        getCustomerDTO.setCustomerNik(opt.get().getCustomerNik());
        getCustomerDTO.setCustomerKk(opt.get().getCustomerKk());
        getCustomerDTO.setCustomerEmail(opt.get().getCustomerEmail());
        getCustomerDTO.setCustomerAddress(opt.get().getCustomerAddress());
        getCustomerDTO.setCustomerTelp(opt.get().getCustomerTelp());
        getCustomerDTO.setCustomerHp(opt.get().getCustomerHp());
        getCustomerDTO.setSalesId(opt.get().getSales().getSalesId());
        getCustomerDTO.setCustomerStatus(opt.get().getCustomerStatus());

        return getCustomerDTO;
    }

    public GetCustomerDTO getAll(String customerId) {
        GetCustomerDTO getCustomerDTO = new GetCustomerDTO();
        Optional<Customer> opt = cr.getCustomerByIdV2(customerId);

        getCustomerDTO.setCustomerId(opt.get().getCustomerId());
        getCustomerDTO.setCustomerNama(opt.get().getCustomerName());
        getCustomerDTO.setDealerId(opt.get().getDealer().getDealerId());
        getCustomerDTO.setCustomerGender(opt.get().getCustomerGender());
        getCustomerDTO.setCustomerNik(opt.get().getCustomerNik());
        getCustomerDTO.setCustomerKk(opt.get().getCustomerKk());
        getCustomerDTO.setCustomerEmail(opt.get().getCustomerEmail());
        getCustomerDTO.setCustomerAddress(opt.get().getCustomerAddress());
        getCustomerDTO.setCustomerTelp(opt.get().getCustomerTelp());
        getCustomerDTO.setCustomerHp(opt.get().getCustomerHp());
        getCustomerDTO.setSalesId(opt.get().getSales().getSalesId());
        getCustomerDTO.setCustomerStatus(opt.get().getCustomerStatus());

        return getCustomerDTO;
    }

    public List<ListAllDTO> listAll(String dealerId, String customerId, int limit, int offset) {
        List<ListAllDTO> listAll = new ArrayList<ListAllDTO>();
        List<Customer> opt = cr.listAll(dealerId, customerId, limit, offset);

        for (int i = 0; i < opt.size(); i++) {
            ListAllDTO listCustomer = new ListAllDTO();
            listCustomer.setCustomerId(opt.get(i).getCustomerId());
            listCustomer.setCustomerNama(opt.get(i).getCustomerName());
            listCustomer.setDealerId(opt.get(i).getDealer().getDealerId());
            listCustomer.setCustomerGender(opt.get(i).getCustomerGender());
            listCustomer.setCustomerNik(opt.get(i).getCustomerNik());
            listCustomer.setCustomerKk(opt.get(i).getCustomerKk());
            listCustomer.setCustomerEmail(opt.get(i).getCustomerEmail());
            listCustomer.setCustomerAddress(opt.get(i).getCustomerAddress());
            listAll.add(listCustomer);
        }
        return listAll;
    }


    public GetCustomerById getCustomerById(String customerId) {
        GetCustomerById cusById = new GetCustomerById();
        Optional<Customer>opt = cr.findById(customerId);
        cusById.setCustomerId(opt.get().getCustomerId());
        cusById.setCustomerNama(opt.get().getCustomerName());
        cusById.setDealerId(opt.get().getDealer().getDealerId());
        cusById.setCustomerGender(opt.get().getCustomerGender());
        cusById.setCustomerNik(opt.get().getCustomerNik());
        cusById.setCustomerKk(opt.get().getCustomerKk());
        cusById.setCustomerEmail(opt.get().getCustomerEmail());
        cusById.setCustomerAddress(opt.get().getCustomerAddress());

        return cusById;


    }
    public Optional<Customer> findByCustomerId(String customerId){
        return cr.findById(customerId);
    }

    public Optional<Customer> getCustomerByIdV3(String customerId){
        return  cr.getCustomerByIdV3(customerId);
    }
}