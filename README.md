# 起動方法

```
Invoke-RestMethod -Uri "http://localhost:8081/mail/send" `
  -Method Post `
  -ContentType "application/json" `
  -Body '{"to":"○○宛先アドレス○○","subject":"テスト","text":"Springから送信テストです"}'

curl `
  -X POST `
  "http://localhost:8081/mail/send" `
  -H "Content-Type: application/json" `
  -d '{
    "to": "○○宛先アドレス○○",
    "subject": "テスト",
    "text": "Springから送信テストです"
  }'
```
<img width="231" height="53" alt="image" src="https://github.com/user-attachments/assets/bbdc11ae-0fc8-4c9d-8d2f-dbd327509114" />

# 全体像
```
MailController
    ↓
MailService
    ↓
mailSender.send(...)
    ↓
smtp.gmail.com:587
    ↓
Gmailがメールを送る
```

# 学んだことメモ

## spring-boot-starter-mail
メール送信機能一式をまとめて追加するパック
```
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-mail</artifactId>
</dependency>
```
 - Jakarta Mail（旧JavaMail）が自動で入る
 - JavaMailSender が自動でBean登録される
 - application.yml の mail 設定が有効になる


## JavaMailSender
メール送信用インターフェース
 - SMTPサーバーに接続
 - 認証
 - メールを組み立て
 - 送信
を全部やってくれる。


## SimpleMailMessage
```
SimpleMailMessage msg = new SimpleMailMessage();
msg.setTo(...);
msg.setSubject(...);
msg.setText(...);
```


## sendメソッド
```
mailSender.send(msg);
```
実際に送る
```
SMTPサーバーに接続
↓
ログイン
↓
データ送信
↓
OKなら終了
```
<img width="849" height="832" alt="image" src="https://github.com/user-attachments/assets/20d7ddf8-156e-478f-b430-09e129709be0" />


## smtp.gmail.com
Gmailの送信専用サーバー  
Gmailアカウントを持っている人が、自分のアカウントとして送る場合のみ使える  
 - 1日 約500通（無料Gmail）
 - Google Workspaceなら約2000通
 - 怪しい挙動するとブロック
 - 短時間大量送信すると止められる

## port: 587
SMTP(暗号化)ポート

# 他のメール配信サービス
| 項目      | SendGrid | Customers Mail Cloud |
| ------- | -------- | -------------------- |
| 国       | アメリカ     | 日本                   |
| 対象      | 開発者〜企業   | 企業向け寄り               |
| API     | 充実       | あり                   |
| 日本語サポート | △        | ◎                    |
| 無料枠     | あり       | 少なめ/要確認              |
