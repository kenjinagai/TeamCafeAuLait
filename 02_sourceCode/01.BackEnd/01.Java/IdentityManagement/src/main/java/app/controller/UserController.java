package app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.entity.User;
import app.repository.UserRepository;
import app.service.AuthrizationService;

/**
 * デモ用データ取得用コントローラ
 * API単位で認可設定を行う
 *
 */
@RestController
public class UserController {

	@Autowired
	private UserRepository repository;

	@Autowired
	private AuthrizationService authrization;

	@RequestMapping(value="/users",method=RequestMethod.GET)
	public ResponseEntity<List<User>> findAll(@RequestHeader(value = "X-XSRF-TOKEN") String token, HttpServletRequest request){
		if(!authrization.isAdmin(request)){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<List<User>>(repository.findAll(),HttpStatus.OK);
	}
}
