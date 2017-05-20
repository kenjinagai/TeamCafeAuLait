package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.entity.User;
import app.repository.UserRepository;

/**
 * デモ用データ取得用コントローラ
 * API単位で認可設定を行う
 *
 */
@RestController
public class UserController {

	@Autowired
	private UserRepository repository;

	@RequestMapping(value="/api/v1/users",method=RequestMethod.GET)
	public List<User> findAll(){
		return repository.findAll();
	}
}
