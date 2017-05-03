package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import app.entity.User;
import app.model.LoginUserDetail;
import app.repository.UserRepository;

/**
 * 認証ユーザ取得サービスクラス
 *
 */
@Service
public class LoginUserDetailsService implements UserDetailsService{
	@Autowired
	UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
		//DBに登録されているメールアドレスから認証対象ユーザを取得
		User user = repository.findOne(userName);
		if(user == null){
			//TODO:このexceptionをそのままcatchしたい
			throw new UsernameNotFoundException("対象データがありません");
		}
		//取得したUserエンティティをもとにLoginUserDetailsを作成する
		return new LoginUserDetail(user);
	}
}