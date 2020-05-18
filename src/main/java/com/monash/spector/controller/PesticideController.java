
package com.monash.spector.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.monash.spector.model.Pesticide;
import com.monash.spector.service.PestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * Controller class for avoid pesticide page
 */
@Controller
public class PesticideController {
    @Autowired
    private PestService pestService;

    @RequestMapping("/pesticide")
    public String viewPesticide(){
        return "pesticide";
    }


    /**
     * user search pesticide data by search label name
     * @param data
     * @return matched pesticide data
     */
    @PostMapping("/pesticide/result")
    public ResponseEntity<?> getSearchedPest(@RequestBody String data ){
        JsonObject jobj = (JsonObject)new JsonParser().parse(data);
        String param = jobj.get("param").getAsString();
        List<Pesticide> pests  = pestService.getSearchedResult(param);

        return ResponseEntity.ok(new Gson().toJson(pests));
    }


}
