package com.paperfox.order.controllers;

import com.paperfox.order.models.params.CalculatorParams;
import com.paperfox.order.models.products.PrintingProduct;
import com.paperfox.order.models.types.MaterialGroups;
import com.paperfox.order.services.OptionsCalculatorParams;
import com.paperfox.order.services.PriceCalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class PriceCalculatorController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private PriceCalculatorService calculatorService;
    @Autowired
    private OptionsCalculatorParams calculatorParams;

    @RequestMapping(value = "/api/calc", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    private PrintingProduct calcProduct(@RequestBody PrintingProduct product) throws Exception {
        logger.info("Create price calculation request: " + product);
        return calculatorService.calculate(product);
    }

    @GetMapping(value = "/api/stickers/getInitialParams")
    private CalculatorParams getInitialParams(@RequestParam String type) throws Exception {

        // todo  create service with factory that return CalculatorParams

        if (MaterialGroups.SELF_ADHESIVE.name().equals(type)) {
            return calculatorParams.getSelfAdhesiveCalculatorParams();
        }

        throw new Exception("Type does not exist");
    }
}
