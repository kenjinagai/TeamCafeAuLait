# mysql環境構築

[TOC]

## mysqlダウンロード/インストール

* mysql-server
	* https://dev.mysql.com/downloads/windows/installer/5.7.html
	* mysqlのid/パスワードは以下に設定する
		* id: root
		* pass: root

* mysql-workbench
	* https://dev.mysql.com/downloads/workbench/

## 初期SQLを実行する
1. `File > Open SQL Script`を実行し、以下のファイルを開く
	* `TeamCafeAuLait\02_sourceCode\03.SQL\01.Init\user.sql`
2. 以下を実行する
	* `Query > Execute (All or Selection)`