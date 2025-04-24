package com.railway.helloworld.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.railway.helloworld.controller.AirportModel.ICAO;
import com.railway.helloworld.controller.AirportModel.Temperature;
import com.railway.helloworld.controller.bourseModel.BourseResult;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import java.util.Optional;


@RestController
@RequestMapping(path = "")
public class Controller {

    private RestTemplate restTemplate;

    static String KeyBourse = "demo";// "WTV9OZSL74MAM5Q2.";
    //https://bootcamptoprod.com/exposing-a-local-spring-boot-app-to-the-web/
    //mathieu.merien@etudiant.univ-rennes.fr mdp 571260lolo
    ObjectMapper objectMapper ;
    public Controller(RestTemplate restTemplate, ObjectMapper objectMapper) throws JsonProcessingException {
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;

    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> myapi(@RequestParam("queryAirportTemp") Optional<String> queryAirportTemp , @RequestParam("queryStockPrice") Optional<String> queryStockPrice  , @RequestParam("queryEval") Optional<String> queryEval  ) throws JsonProcessingException {
        if(queryAirportTemp.isPresent()){
            Integer temp=0;
            String code=queryAirportTemp.get();
            String queryAnswer=this.restTemplate.getForObject("https://airport-data.com/api/ap_info.json?iata="+code, String.class);
            ICAO data = objectMapper.readValue(queryAnswer, ICAO.class);
            String icaoCode=data.getIcao();
            String queryAnswer2=this.restTemplate.getForObject("https://aviationweather.gov/api/data/metar?ids="+icaoCode+"&format=json", String.class);
            Temperature[] result=objectMapper.readValue(queryAnswer2, Temperature[].class);
            temp=(int)Double.parseDouble(result[0].getTemp());
            return ResponseEntity.ok(temp);
        }
        if(queryStockPrice.isPresent()){
            String code=queryStockPrice.get();

            String queryAnswer=this.restTemplate.getForObject("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol="+code+"&interval=5min&apikey="+Controller.KeyBourse, String.class);
            BourseResult data =objectMapper.readValue(queryAnswer,BourseResult.class);
            String timeseriesname = data.getData().keySet().stream().min((a, b) -> b.compareTo(a))
                    .orElse(null);
            String price = data.getData().get(timeseriesname).getLow();
            ;
            assert(data.getMetadata().getSymbole().equals(code)):data.getMetadata().getSymbole()+" != "+code;

            return ResponseEntity.ok((int)Double.parseDouble(price));
        }
        if(queryEval.isPresent()){
            Integer stock=2;
            System.out.println(queryEval.get());
            //TODO do the math
            return ResponseEntity.ok(stock);
        }
        else{
            return ResponseEntity.ok(0);
        }



    }


}
