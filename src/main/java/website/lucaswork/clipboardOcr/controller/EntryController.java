package website.lucaswork.clipboardOcr.controller;

import com.baidu.aip.ocr.AipOcr;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class EntryController {

    @Autowired
    AipOcr aipOcr;

    @GetMapping("/paste")
    public String pasteEntry() {
        return "paste";
    }

    @PostMapping("paste")
    @ResponseBody
    public String pasteProcessing(@RequestParam("avatar") MultipartFile file) throws IOException {
        byte [] bytes = file.getBytes();
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");
        JSONObject ret = aipOcr.basicGeneral(bytes, options);
        Integer resultNum = ret.getInt("words_result_num");
        List<String> words = new ArrayList<>();
        if (resultNum > 0) {
            JSONArray resultArr = ret.getJSONArray("words_result");
            for(Object obj : resultArr) {
                JSONObject json = (JSONObject) obj;
                    words.add(json.getString("words"));
            }
        }
        return StringUtils.join(words, '\n');
    }
}
