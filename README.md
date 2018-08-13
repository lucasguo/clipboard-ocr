# clipboard-ocr
Util Baidu OCR interface to recognize text from a clipboard image.
Based on Spring Boot.

### How to make it work
1. Apply aip function from ![Baidu OCR](https://cloud.baidu.com/product/ocr.html)
2. Fill the applied app id/ app key/ secret into application.properties
3. Start up the project
4. Visit localhost:8080/paste
5. Paste your image into the textarea then the result will display

# 剪贴板图像识别
利用百度OCR接口实现的识别剪贴板图片。
基于Spring Boot，从四面八方复制粘贴代码拼凑而成。

### 使用方法
1. 到![百度文字识别](https://cloud.baidu.com/product/ocr.html)申请使用账号
2. 将申请到的app id/app key/secret key放到application.properties
3. 启动工程
4. 访问localhost:8080/paste
5. 把剪贴板的图片粘贴到文本域里，结果就会出现
