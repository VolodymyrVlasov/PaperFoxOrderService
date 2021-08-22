package com.paperfox.order.services.calculator;

import com.paperfox.order.controllers.OrderController;
import com.paperfox.order.models.materials.Material;
import com.paperfox.order.models.params.CalculatorParams;
import com.paperfox.order.models.products.PrintingProduct;
import com.paperfox.order.models.types.CuttingType;
import com.paperfox.order.models.types.ProductGroupType;
import com.paperfox.order.repositories.calculator.FakeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PriceCalculatorService implements IPriceCalculatorService {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    //    @Autowired
    public FakeRepository repository;

    @Override
    public PrintingProduct calculate(PrintingProduct product) throws Exception {
        product.setMaterial(FakeRepository.getMaterialByMaterialType(product.getMaterial().getMaterialType()));
        ProductGroupType productGroupType = product.getProductGroup().getProductGroupType();
        if (productGroupType == ProductGroupType.DRAWING) {
            logger.info("Calc request: " + product.getProductGroup());
        } else if (productGroupType == ProductGroupType.BIZ_CARD) {
            logger.info("Calc request: " + product.getProductGroup());
        } else if (productGroupType == ProductGroupType.POSTER) {
            logger.info("Calc request: " + product.getProductGroup());
        } else if (productGroupType == ProductGroupType.MAGNET) {
            logger.info("Calc request: " + product.getProductGroup());
        } else if (productGroupType == ProductGroupType.FLYER) {
            logger.info("Calc request: " + product.getProductGroup());
        } else if (productGroupType == ProductGroupType.STICKER) {
            return new StandardShapeStickerCalculator().calcProduct(product);
        } else if (productGroupType == ProductGroupType.PHOTO) {
            logger.info("Calc request: " + product.getProductGroup());
        }
        throw new Exception("Calculation for " + product.getProductGroup() + " product type is not defined,");
    }

    @Override
    public CalculatorParams getRenderParamsByType(String materialGroupType) {
        Map<String, Integer> cuttingType = new HashMap<>();
        List<Material> materials = FakeRepository.getMaterialsByMaterialGroupType(materialGroupType);

        for (CuttingType e : CuttingType.values()) {
            cuttingType.put(e.name(), e.name);
        }

        return new CalculatorParams(materials, cuttingType);
    }
}
