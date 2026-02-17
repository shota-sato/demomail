# Github Push手順

```
git init

git add .

git commit -m "Initial mail demo app with env-based credentials"

Giuhubでリポジトリ作成

git remote -v
origin  git@github.com:shota-sato/demomail.git (fetch)
origin  git@github.com:shota-sato/demomail.git (push)

git branch
* master
git branch -M main

git branch        
* main

git push -u origin main

git remote set-url origin https://github.com/宛先

git remote -v
origin  https://github.com/shota-sato/demomail.git (fetch)
origin  https://github.com/shota-sato/demomail.git (push)

git push -u origin main
```


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

