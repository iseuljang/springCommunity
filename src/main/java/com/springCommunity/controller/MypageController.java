package com.springCommunity.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.springCommunity.service.MypageService;
import com.springCommunity.vo.MypageVO;

@Controller
public class MypageController {
	
	@Autowired
	public MypageService mypageService;

	@Autowired
	public JavaMailSenderImpl mailSender;
	
	@RequestMapping(value="/mypage/info.do", method = RequestMethod.GET)
	public String info(Principal principal, Model model) {
		
		String userId = principal.getName();
		
		MypageVO vo = mypageService.selectOne(userId);
		model.addAttribute("vo",vo);

		return "mypage/info";
	}
	
	@RequestMapping(value="/mypage/info.do", method = RequestMethod.POST)
	public String info(MypageVO mypageVO,Principal principal,HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		String userId = principal.getName();
		mypageVO.setUser_id(userId);
		
		int rs = mypageService.userUpdate(mypageVO);
			
		if(rs>0) {
			System.out.println("회원정보 수정 성공.");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().append("<script>alert('회원정보 수정에 성공했습니다.');"
										+"location.href='"
										+request.getContextPath()
										+"/mypage/info.do'</script>").flush();
			
		}else {
			System.out.println("회원정보 수정 실패.");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().append("<script>alert('회원정보 수정 실패했습니다. 다시확인해주세요');"
										+"location.href='"
										+request.getContextPath()
										+"/mypage/info.do'</script>").flush();
			
		}
		return "redirect:info.do";
	}
		
	@ResponseBody
	@RequestMapping(value="/mypage/info/pwChangeModal.do", method = RequestMethod.GET)
	public String pwChangeModal(String pwChangeTable) {
		return "success";
	}	
	
	@ResponseBody
	@RequestMapping(value = "/mypage/info/sendMail.do", method = RequestMethod.POST)
	public String sendMail(String userEmail) {		
		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111;
		System.out.println("checkNum::::::::::=>"+checkNum);
		System.out.println("email::::::::::"+userEmail);
		/* 이메일 보내기 */
        String setFrom = "sgh9948@gmail.com";
        String toMail = userEmail;
        String title = "회원가입 인증 이메일 입니다.";
        String content = 
                "홈페이지를 방문해주셔서 감사합니다." +
                "<br><br>" + 
                "인증 번호는 " + checkNum + "입니다." + 
                "<br>" + 
                "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content,true);
            mailSender.send(message);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return Integer.toString(checkNum);
	}
	
	@ResponseBody
	@RequestMapping(value="/mypage/info/pwChange.do")
	public String pwChange(MypageVO mypageVO,Principal principal) {
		
		String userId = principal.getName();
		mypageVO.setUser_id(userId);
		
		BCryptPasswordEncoder epwe = new BCryptPasswordEncoder();
		String encodedPassword = epwe.encode(mypageVO.getUser_password());
		mypageVO.setUser_password(encodedPassword);
		System.out.println("암호화된 비밀번호: " + encodedPassword);
		
		int rs = mypageService.userPwUpdate(mypageVO);
		
		if (rs > 0) {
			System.out.println("success");
			return "success";
		} else {
			System.out.println("error");
			return "error";
		}
	}
	
	//--------------------------------------------------------------info끝 benefit시작
	@RequestMapping(value="/mypage/benefit.do", method = RequestMethod.GET)
	public String benefit(Principal principal, Model model) {
		String userId = principal.getName();
		
		MypageVO vo = mypageService.selectOne(userId);
		model.addAttribute("vo",vo);
		
		return "mypage/benefit";
	}
	
	@ResponseBody
	@RequestMapping(value="/mypage/benefit.do", method = RequestMethod.POST, produces="application/json;charset=UTF8")
	public MypageVO benefit(MypageVO mypageVO,Principal principal,@RequestParam(value="fileName")List<MultipartFile> multiFile
			,HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException {
		
		String userId = principal.getName();
		mypageVO.setUser_id(userId);
		int rs = mypageService.insertBenefit(mypageVO);
				
		String path = request.getSession().getServletContext().getRealPath("/resources/upload");
		
		System.out.println("upload path :: "+path);
		
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		for(MultipartFile file : multiFile) { 
			if(!file.getOriginalFilename().isEmpty()) {
				UUID uuid = UUID.randomUUID();
				String fileRealName=uuid.toString()+file.getOriginalFilename();
				
				file.transferTo(new File(path,fileRealName));
				mypageVO.setFile_name(file.getOriginalFilename());
				mypageVO.setAttach_name(fileRealName);
				int result = mypageService.insertAttach(mypageVO);
			}
		}
		
		if(rs>0) {
			System.out.println("등록완료");
			MypageVO vo = mypageService.selectOneRequest(mypageVO.getRequest_no());
			return vo;
			
		}else {
		}
		return null;
	}
	
	//--------------------------------------------------------------benefit끝 medical시작
	
	@RequestMapping(value="/mypage/medical.do", method = RequestMethod.GET)
	public String medical() {
		return "mypage/medical";
	}
	
	@ResponseBody
	@RequestMapping(value="/mypage/medical.do", method = RequestMethod.POST, produces="application/json;charset=UTF8")
	public MypageVO medical(MypageVO mypageVO,Principal principal,@RequestParam(value="fileName")List<MultipartFile> multiFile
			,HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException {
		
		String userId = principal.getName();
		mypageVO.setUser_id(userId);
		
		int rs = mypageService.insertMedical(mypageVO);
		
		String path = request.getSession().getServletContext().getRealPath("/resources/upload");
		
		System.out.println("upload path :: "+path);
		
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		for(MultipartFile file : multiFile) {
			if(!file.getOriginalFilename().isEmpty()) {
				UUID uuid = UUID.randomUUID();
				String fileRealName=uuid.toString()+file.getOriginalFilename();
				
				file.transferTo(new File(path,fileRealName));
				mypageVO.setFile_name(file.getOriginalFilename());
				mypageVO.setAttach_name(fileRealName);
				int result = mypageService.insertAttach(mypageVO);
			}
		}
		
		if(rs>0) {
			System.out.println("등록완료");
			MypageVO vo = mypageService.selectOneRequest(mypageVO.getRequest_no());
			return vo;
			
		}else {
		}
		return null;
	}
	
}