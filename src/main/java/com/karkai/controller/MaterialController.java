package com.karkai.controller;

import com.google.api.client.json.Json;
import com.karkai.modal.GetMaterial;
import com.karkai.modal.Material;
import com.karkai.modal.MaterialFolder;
import com.karkai.service.JsonService;
import com.karkai.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import net.minidev.json.parser.ParseException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "material")
public class MaterialController {
    @Autowired
    private MaterialService materialService;


    //  create new User
    @PostMapping("/add")
    public String addNewMaterial(@RequestBody MaterialFolder material){
        return materialService.addMaterial(material);
    }

    //  create new User
    @PostMapping("/getBySubject")
    public List<Material> getAllMaterials(@RequestBody GetMaterial material) throws ExecutionException, InterruptedException, IOException, ParseException {
        return materialService.getMaterialBySubject(material.getSubject(),material.getExam(),material.getLanguage());
    }

}
