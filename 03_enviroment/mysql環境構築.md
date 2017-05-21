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
1. `File > New Query Tab`を選択する
2. userデータベースを作成する
	1. `CREATE DATABASE user`を入力する。
	2. 雷マークを押下し、SQLを実行する。
3. 初期データベースを復元する
	1. `File > Open SQL Script`を実行し、以下のファイルを開く
		* `TeamCafeAuLait\02_sourceCode\03.SQL\01.Init\user.sql`
	2. 以下を実行する
		* `Query > Execute (All or Selection)`