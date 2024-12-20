package com.springCommunity.controller;

import java.io.InputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.springCommunity.service.AdminService;
import com.springCommunity.service.ExcelUserService;
import com.springCommunity.service.NaverEmailService;
import com.springCommunity.service.UserService;
import com.springCommunity.util.PagingUtil;
import com.springCommunity.vo.SearchVO;
import com.springCommunity.vo.UserInfoVO;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @Autowired
	UserService userService;
    
    @Autowired
	AdminService adminService;
	
	@Autowired
	NaverEmailService naverEmailService;
    
    @Autowired
    ExcelUserService excelUserService;

    @RequestMapping(value="/upload_users.do", method = RequestMethod.GET)
    public String uploadUsers() {
        return "admin/upload_users";
    }
    
    @PostMapping("/upload_users.do")
    public String uploadUsers(@RequestParam("file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("파일이 비어 있습니다.");
        }
        
        try(InputStream inputStream = file.getInputStream()) {
            List<UserInfoVO> users = excelUserService.readUsersFromExcel(inputStream);

            // 비밀번호 암호화
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            for(UserInfoVO user : users) {
            	user.setBefore_password(user.getUser_password());
                user.setUser_password(encryptPassword(user.getUser_password()));
            }

            // MyBatis 매퍼 호출
            int result = adminService.insertUsers(users);
            
            if(result > 0) {
                model.addAttribute("Message", "사용자 정보가 성공적으로 업로드되었습니다.");
                // 이메일 발송 및 실패 처리
                List<String> failedEmails = sendEmailToUsers(users);
                if(!failedEmails.isEmpty()) {
                    model.addAttribute("MessageE", "이메일 발송 실패: " + String.join(", ", failedEmails));
                }else {
                	model.addAttribute("MessageE", "회원정보가 이메일로 성공적으로 발송되었습니다.");
                }
            }else {
                model.addAttribute("Message", "파일 처리 중 오류가 발생했습니다.");
            }
            
            return "admin/upload_users";
        }catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("Message", "파일 처리 중 오류가 발생했습니다.");
            return "admin/upload_users";
        }
    }
    
    @RequestMapping(value="/insert_user.do", method = RequestMethod.POST)
    public String insertUser(UserInfoVO userInfoVO, Model model) {
        BCryptPasswordEncoder epwe = new BCryptPasswordEncoder();
        
        String random_PASSWORD = generateRandom(6);
        userInfoVO.setUser_password(random_PASSWORD); // 비밀번호는 암호화 필요
        
        String encodedPassword = epwe.encode(userInfoVO.getUser_password());
        userInfoVO.setUser_password(encodedPassword);
        System.out.println("암호화된 비밀번호: " + encodedPassword);
        
        userInfoVO.setUser_id("jj" + userInfoVO.getUser_id());
        
        int result = userService.insertUser(userInfoVO);
        
        // 회원 등록 성공 시 이메일 발송
        if(result > 0) {
            System.out.println("회원등록성공");
            model.addAttribute("iMessage", "사용자 정보가 성공적으로 등록되었습니다.");
            // 이메일 발송 내용 설정
            String subject = "회원 가입 정보";
            String emailContent = "안녕하세요, " + userInfoVO.getUser_name() + "님!\n\n" +
                                  "귀하의 회원 가입이 완료되었습니다.\n" +
                                  "회원 ID: " + userInfoVO.getUser_id() + "\n" +
                                  "임시 비밀번호: " + random_PASSWORD + "\n\n" +
                                  "로그인 후 비밀번호를 변경하시기 바랍니다.";
            
            try {
            	naverEmailService.sendNaverEmail(userInfoVO.getUser_email(), subject, emailContent);
                System.out.println("이메일 발송 성공");
                model.addAttribute("iMessageE", "회원정보가 이메일로 성공적으로 발송되었습니다.");
            } catch (MailException e) {
                e.printStackTrace();
                System.out.println("이메일 발송 실패");
                model.addAttribute("iMessageE", "이메일 발송에 실패했습니다.");
            }
        } else {
            System.out.println("회원등록실패");
            model.addAttribute("iMessage", "사용자 정보 등록에 실패했습니다.");
        }
        
        return "admin/upload_users";
    }
    
    //랜덤 비밀번호 6글자 생성
  	public String generateRandom(int length) {
  		String datas = "abcdefghijklmnopqrstuvwxyz0123456789";
  		SecureRandom secureRandom = new SecureRandom();
  		StringBuilder code = new StringBuilder();
  		
  		for (int i = 0; i < length; i++) {
  			int rand = secureRandom.nextInt(datas.length());
  			code.append(datas.charAt(rand));
  		}
  		
  		return code.toString();
  	}
    
    // 비밀번호 암호화 메소드
    private String encryptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
    
    @RequestMapping(value="/list.do", method = RequestMethod.GET)
    public String userList(Model model, SearchVO searchVO
			, @RequestParam(value="nowPage", required = false, defaultValue = "1") int nowPage) {
		
    	if(searchVO.getFirst_date() != null && searchVO.getFirst_date().trim().isEmpty()) {
            searchVO.setFirst_date(null);
        }
        if(searchVO.getSecond_date() != null && searchVO.getSecond_date().trim().isEmpty()) {
            searchVO.setSecond_date(null);
        }
    	
		int total = adminService.selectCount(searchVO);
		System.out.println("회원 수:" + total);
		
		PagingUtil paging = new PagingUtil(nowPage,total,10);
		
		searchVO.setStart(paging.getStart());
		searchVO.setPerPage(paging.getPerPage());
		
		List<UserInfoVO> userList = adminService.selectAll(searchVO);
		model.addAttribute("userList", userList);
		model.addAttribute("paging", paging);
		
		return "admin/list";
	}
    
    private List<String> sendEmailToUsers(List<UserInfoVO> users) {
	    List<String> failedEmails = new ArrayList<>();

	    for(UserInfoVO user : users) {
	        String subject = "회원 가입 정보";
	        String emailContent = "안녕하세요, " + user.getUser_name() + "님!\n\n" +
	                              "귀하의 회원 가입이 완료되었습니다.\n" +
	                              "회원 ID: " + user.getUser_id() + "\n" +
	                              "임시 비밀번호: " + user.getBefore_password() + "\n\n" +
	                              "로그인 후 비밀번호를 변경하시기 바랍니다.";
	        try{
	            naverEmailService.sendNaverEmail(user.getUser_email(), subject, emailContent);
	            System.out.println("이메일 발송 성공: " + user.getUser_email());
	        }catch (MailException e) {
	            e.printStackTrace();
	            System.out.println("이메일 발송 실패: " + user.getUser_email());
	            failedEmails.add(user.getUser_email()); // 실패한 이메일 추가
	        }
	    }

	    return failedEmails;
	}
    
    @ResponseBody
	@PostMapping("/updateUser.do")
	public UserInfoVO updateUser(UserInfoVO vo) {
		UserInfoVO user = null;
		int result = adminService.updateUser(vo);
		if(result > 0) {
			System.out.println("수정성공");
			user = adminService.selectById(vo.getUser_id());
		}
		return user;
	}
}
