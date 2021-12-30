package com.hp.dit.police.inventory.Controller.stockin;

import com.hp.dit.police.inventory.entities.DistrictMaster;
import com.hp.dit.police.inventory.entities.StockInEntity;
import com.hp.dit.police.inventory.repositories.districtRepository.DistrictRepository;
import com.hp.dit.police.inventory.repositories.stockin.StockInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class StockInRestController {

    @Autowired
    StockInRepository stockInRepository;

    @RequestMapping(value = "/api/stokin", method = RequestMethod.POST)
    public DataTablesOutput<StockInEntity> listPOST(@Valid @RequestBody DataTablesInput input) {
        return stockInRepository.findAll(input);
    }
}
