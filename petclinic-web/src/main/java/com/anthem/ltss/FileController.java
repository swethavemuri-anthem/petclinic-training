package com.anthem.ltss;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;



@Controller
public class FileController {

	@GetMapping(value="/Anthem/upload", produces = "application/json")
    public @ResponseBody String uploadSingleFile(@RequestParam(name="file", required=false) MultipartFile file ) {
		UUID uuid = UUID.randomUUID();
		return uuid.toString() ;
    }
	
//	@GetMapping(value="/Anthem/loadCases", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Page<CMCase>> loadCases(@RequestParam int pageSize, @RequestParam int page, @RequestParam String[] sortBy, HttpServletRequest req) {
////        Page<CMCase> pageResult = getCases();
////        return new ResponseEntity<>(pageResult, HttpStatus.OK);
//    }
	
//	public Page<CMCase> getCases() {
//		
//		List<CMCase> cases = new ArrayList<CMCase>();
//		CMCase c1 = new CMCase();
//		c1.setId(Long.valueOf("1"));
//		c1.setStatus("Initial");
//		cases.add(c1);
//		CMCase c2 = new CMCase();
//		c2.setId(Long.valueOf("2"));
//		c2.setStatus("Cancelled");
//		cases.add(c2);
//		CMCase c3 = new CMCase();
//		c3.setId(Long.valueOf("3"));
//		c3.setStatus("Processing");
//		cases.add(c3);
//		return new PageImpl<>(cases, null, cases.size());
//		
//	}

}