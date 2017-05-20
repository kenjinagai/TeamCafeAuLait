package app.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 認証結果を返すモデルクラス.
 * @author Kenji Nagai
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResult {
	/** ユーザー 名. */
	private String userName;
	/** 権限一覧. */
	private List<String> permissionList;
	/** 役職一覧. */
	private List<String> roleList;
}
