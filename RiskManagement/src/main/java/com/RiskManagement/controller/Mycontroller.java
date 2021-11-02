package com.RiskManagement.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.juli.logging.Log;
//import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RiskManagement.dao.ApplicationsRepo;
import com.RiskManagement.entities.ActionReports;
import com.RiskManagement.entities.Applications;
import com.RiskManagement.entities.ApprovedApplications;
import com.RiskManagement.entities.AuthRequest;
import com.RiskManagement.entities.EmailRequest;
import com.RiskManagement.entities.JwtResponse;
import com.RiskManagement.entities.JwtResponseGetApplication;
import com.RiskManagement.entities.LoginResponse;
import com.RiskManagement.entities.Otp;
import com.RiskManagement.entities.StoreOtp;
import com.RiskManagement.services.EmailService;
import com.RiskManagement.services.OtpService;
import com.RiskManagement.services.services;
import com.RiskManagement.util.JwtUtil;


@RestController
@RequestMapping("riskmanagement")
public class Mycontroller {
	@Autowired
	public OtpService otpservice;
	@Autowired
    private EmailService emailService;
	@Autowired
	public services serve;
	 @Autowired
	    private JwtUtil jwtUtil;
	    @Autowired
	    private AuthenticationManager authenticationManager;
	    @Autowired
		public ApplicationsRepo applicationrepo;
	    
	    private static final Logger logger = LoggerFactory.getLogger(Mycontroller.class);
	
	@GetMapping("/home")
	public ResponseEntity<String> home() {
		return ResponseEntity.ok("this is home");
		
		
	}
	@PostMapping("/authenticate")
    public LoginResponse generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		boolean isLoginSuccess = false;
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
            
            isLoginSuccess = auth.isAuthenticated();
        } catch (Exception ex) {
        	isLoginSuccess = false; 
        	 logger.debug("Authentication failed. Username : " + authRequest.getUserName() + " password: " + authRequest.getPassword());
        }
        
        LoginResponse loginresponse = new LoginResponse();
        if(isLoginSuccess) {
        	String result = jwtUtil.generateToken(authRequest.getUserName());
            if (result==null || result.isEmpty()) {
            	loginresponse.setResult("faliur");
            	loginresponse.setMessage("inavalid username/password");
            	return loginresponse;
            	
            	
            }else {
            	loginresponse.setResult("success");
            	loginresponse.setMessage("login was successfull");
            	loginresponse.setToken(result);
            	return loginresponse;
            	
            	
            }
        } else {
        	loginresponse.setResult("faliur");
        	loginresponse.setMessage("inavalid username/password");
        	return loginresponse;
        }
        
    }
	@PostMapping("/home/application")
	public ResponseEntity<JwtResponse> addapplication(@RequestBody Applications user) {
		JwtResponse jwtresponse = new JwtResponse();
		int temp = user.getCid();
		boolean application = applicationrepo.existsById(temp);
		
		if (application == true) {
			jwtresponse.setResult("faliur");
			jwtresponse.setMessage("there is already a application exist please apply after needed action is taken on it ");
			 return ResponseEntity.ok(jwtresponse);
			
		}else {
		this.serve.addapplication(user);
		String result =  this.serve.loansenctioned(user.getCid(), user.getAmount()) ;
		if (result.equals("loan can be granted")) {
			 //jwtresponse.setResult("success");
			 jwtresponse.setMessage("loan can be granted");
			 return ResponseEntity.ok(jwtresponse);
			
		}else if (result.equals("loan cannot be granted")) {
			//jwtresponse.setResult("success");
			 jwtresponse.setMessage("loan cannot be granted");
			 return ResponseEntity.ok(jwtresponse);
		}else if (result.equals("the amount exeeds the limit and will be reviewed ny business analyst")) {
			//jwtresponse.setResult("success");
			 jwtresponse.setMessage("the amount exeeds the limit and will be reviewed ny business analyst");
			 return ResponseEntity.ok(jwtresponse);
		}else {
			//jwtresponse.setResult("success");
			 jwtresponse.setMessage("the amount is too small according to policies");
			 return ResponseEntity.ok(jwtresponse);
			
		}	
		}
		
		
		
	}
	@GetMapping("/home/reviewapplication/{cid}")
	public JwtResponseGetApplication getuser(@PathVariable int cid) {
		JwtResponseGetApplication result = this.serve.getapplication(cid);
		return result;
		
	}
	
			
	
    @GetMapping("/home/pagesort/{offset}/{pagesize}/{field}")
    public Page<Applications> pagesort(@PathVariable int offset,@PathVariable int pagesize,@PathVariable String field){
		return this.serve.Pagesort(offset, pagesize, field);
		
    	
    	
    }
   @RequestMapping("/home/changepassword/{username}/{oldpassword}/{newpassword}")
   public ResponseEntity<JwtResponse> changepassword(@PathVariable String username,@PathVariable String oldpassword,@PathVariable String newpassword) {
	   JwtResponse result = this.serve.changepassword(username, oldpassword, newpassword);
			   return ResponseEntity.ok(result);
   }
   @RequestMapping("/home/forgetpassword/{id}/{username}/{newpassword}")
   public ResponseEntity<JwtResponse> forgetpassword(@PathVariable int id,@PathVariable String username,@PathVariable String newpassword) {
	   JwtResponse result = this.serve.forgetpassword(id, username, newpassword);
			   return ResponseEntity.ok(result);
   }
   @RequestMapping("/home/deleteapplication")
   public ResponseEntity<JwtResponse> deleteapplication(@RequestBody Applications user){
	   JwtResponse result = this.serve.deleteapplication(user);
	   return ResponseEntity.ok(result);
	   
	   
   }
   @PostMapping("/home/action")
   public ResponseEntity<JwtResponse> addreports(@RequestBody ActionReports report){
	   JwtResponse result = this.serve.addreport(report);
	   return ResponseEntity.ok(result);
	   
	   
   }
   @PostMapping("/home/approveapplication")
   public ResponseEntity<JwtResponse> approveapplication(@RequestBody ApprovedApplications application){
	   JwtResponse result = this.serve.approvereport(application);
	   return ResponseEntity.ok(result);
   }
   @PostMapping("/home/sendmail")
   public ResponseEntity<JwtResponse> sendEmail(@RequestBody EmailRequest request){
	   JwtResponse jwtresponse = new JwtResponse();
	   boolean result = this.emailService.sendEmail(request.getSubject(), request.getMessage(), request.getTo());
	   if (result) {
		   jwtresponse.setResult("success");
		   jwtresponse.setMessage("mail has been send");
		   return ResponseEntity.ok(jwtresponse);
		   
		   
		   
	   }else {
		   jwtresponse.setResult("faliure");
		   jwtresponse.setMessage("mail has not been  send");
		   return ResponseEntity.ok(jwtresponse);
		   
	   }
   }
   @PostMapping("/home/generateotp")
   public ResponseEntity<JwtResponse> generateotp(@RequestBody Otp emailid){
	   JwtResponse jwtresponse = new JwtResponse();
	   int otp = this.otpservice.generateOTP(emailid.getEmailid());
	   EmailRequest request = new EmailRequest();
	   request.setTo(emailid.getEmailid());
	   request.setSubject("otp varification");
	   request.setMessage(String.valueOf(otp));
	   boolean result = this.emailService.sendEmail(request.getSubject(), request.getMessage(), request.getTo());
	   if (result) {
		   jwtresponse.setResult("success");
		   jwtresponse.setMessage("otp sent successfully");
		   return ResponseEntity.ok(jwtresponse);
		   
		   
		   
	   }else {
		   jwtresponse.setResult("faliure");
		   jwtresponse.setMessage("cannot send otp");
		   return ResponseEntity.ok(jwtresponse);
		   
	   }
	  
	   
	   
   }
   @PostMapping("/home/verifyotp")
   public ResponseEntity<JwtResponse> verifyotp(@RequestBody StoreOtp body){
	   JwtResponse jwtresponse = new JwtResponse();
	   int otp = this.otpservice.getOtp(body.getEmailid());
	   if (otp==body.getOtp()) {
		   jwtresponse.setResult("success");
		   jwtresponse.setMessage("correct otp");
		   this.otpservice.clearOTP(body.getEmailid());
		   return ResponseEntity.ok(jwtresponse);
		   
	   }else {
		   jwtresponse.setResult("faliure");
		   jwtresponse.setMessage("invalid otp");
		   return ResponseEntity.ok(jwtresponse);
		   
	   }
	   
	   
   }
   
   
}
