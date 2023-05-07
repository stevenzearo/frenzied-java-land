package app.site.api;

import app.site.api.material.GetImageResponse;
import app.site.api.material.SearchMaterialAJAXRequest;
import app.site.api.material.SearchMaterialAJAXResponse;
import app.site.service.MaterialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Steve Zou
 */
@CrossOrigin
@RestController
@RequestMapping("/hland/material")
public class MaterialController {
    private final Logger logger = LoggerFactory.getLogger(MaterialController.class);
    @Autowired
    MaterialService materialService;

    @ResponseBody
    @PostMapping("/article")
    public SearchMaterialAJAXResponse searchMaterial(@RequestParam("access_token") String accessToken, @RequestBody SearchMaterialAJAXRequest request) {
        return materialService.searchMaterial(accessToken, request);
    }

    @ResponseBody
    @GetMapping("/article-image")
    public GetImageResponse getImage(@RequestParam("url") String url) {
        return materialService.getGetImage(url);
    }
}
