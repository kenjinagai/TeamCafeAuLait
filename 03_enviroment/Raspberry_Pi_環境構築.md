# Raspberry Pi 環境構築

## Rasberry Pi インストール
1. 以下のページからRASPBIAN JESSIE WITH PIXELをダウンロードする
	* [RASPBIAN](https://www.raspberrypi.org/downloads/raspbian/)
2. micro usbにisoを焼く


### 日本語化

1. Raspi-configを起動して「Innternnatiolisation Options」を選択
2. Change Localeを選択
3. ja_JP.UTF-8をチェックしてデフォルト言語に選択
4. 以下のコマンドを実行
	* `$ sudo apt-get install ttf-kochi-gothic xfonts-intl-japanese xfonts-intl-japanese-big xfonts-kaname`

### chromeium
* 以下のコマンドを実行する

```bash
# apt-get update
# apt-get dist-upgrade
# apt-get upgrade
```
[Raspberry Piで動作するChromeブラウザ「Chromium」をインストールしよう！](https://www.fabshop.jp/raspberry-pi%E3%81%A7%E5%8B%95%E4%BD%9C%E3%81%99%E3%82%8Bchrome%E3%83%96%E3%83%A9%E3%82%A6%E3%82%B6%E3%80%8Cchromium%E3%80%8D%E3%82%92%E3%82%A4%E3%83%B3%E3%82%B9%E3%83%88%E3%83%BC%E3%83%AB%E3%81%97/
)

### mysqlインストール
```bash
# apt-get install mysql-server
```

* 以下を設定する
	* id: root
	* pass: root

[Debian Jessie に MySQL を入れてみた](http://namotch.hatenablog.com/entry/2015/06/12/223808)

#### mysqlを外部からつなぐ設定
http://d.hatena.ne.jp/uriyuri/20081024/1224798772

### python構築
1. lsusbでカードリーダー認識されているか確認する(Bus 001 Device 006: ID 054c:06c3 Sony Corp.)
```
$ lsusb
Bus 001 Device 007: ID 0411:01ee BUFFALO INC. (formerly MelCo., Inc.) WLI-UC-GNM2 Wireless LAN Adapter [Ralink RT3070]
Bus 001 Device 006: ID 054c:06c3 Sony Corp.
Bus 001 Device 005: ID 04f3:0103 Elan Microelectronics Corp. ActiveJet K-2024 Multimedia Keyboard
Bus 001 Device 004: ID 1bcf:05ca Sunplus Innovation Technology Inc.
Bus 001 Device 003: ID 0424:ec00 Standard Microsystems Corp. SMSC9512/9514 Fast Ethernet Adapter
Bus 001 Device 002: ID 0424:9514 Standard Microsystems Corp.
Bus 001 Device 001: ID 1d6b:0002 Linux Foundation 2.0 root hub
```
2. `$ sudo apt-get install python-pip`
3. `$ sudo pip2 install --upgrade pip`
4. `$ sudo pip2 install nfcpy`
5. `$ sudo python2 /opt/nfcpy/'echo SUBSYSTEM==\"usb\", ACTION==\"add\", ATTRS{idVendor}==\"054c\", ATTRS{idProduct}==\"06c3\", GROUP=\"plugdev\" >> /etc/udev/rules.d/nfcdev.rules'`
6. ICocaを載せる
7. 以下のコマンドを実行し、カード番号を取得する
```
$ python2 /opt/get-card-id.py
0101031288144d05
```
8. tomcat8ユーザーをplugdevグループに属させなければ、nfcpyを実行することができない。
```bash
$ sudo usermod -a -G plugdev tomcat8
```
[Raspberry PiでFelicaのIDmを表示する](http://qiita.com/ihgs/items/34eefd8d01c570e92984)



### フォント追加
```bash
$ cd ~/Downloads
$ wget -O NotoSansCJKjp-hinted.zip https://noto-website-2.storage.googleapis.com/pkgs/NotoSansCJKjp-hinted.zip
$ unzip -d NotoSansCJKjp-hinted NotoSansCJKjp-hinted.zip
$ sudo mkdir -p /usr/share/fonts/opentype
$ sudo mv -fv ./NotoSansCJKjp-hinted /usr/share/fonts/opentype/note
$ rm -rfv NotoSansCJKjp-hinted.zip
$ sudo fc-cache -fv
```
http://studio.beatnix.co.jp/kids-it/hardware/raspberry_pi/system-font-noto/
