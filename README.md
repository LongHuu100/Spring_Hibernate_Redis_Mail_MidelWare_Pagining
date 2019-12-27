# Spring_Hibernate_Redis_Mail_MidelWare_Pagining
Spring cho dự án gồm nhiều module như hibernate, jwt, security, redis, mail, phân trang, midelware và các thành phần khác.

# Tạo các model tự động từ table bằng tool của JBOSS và Hibernate.
# Sử dụng redis cho 2 nhiệm vụ.
- Cache data.
- Pub/Sub: Khi dự án cần giao tiếp với nhiều module ở các hệ thống khác nhau thì có nhiều cách để làm như thông qua db, REST api, nhưng các cách đó không thể tối ưu bằng việc đăng ký một event từ Redis, như vậy nghĩa là sao nhỉ.?
Nghĩa là khi một data được đẩy vào Redis thì Redis sẽ Broadcast đến tất cả những hệ thống nào đăng ký lắng nghe sự kiện với Redis ( trong project thì qua chanel mail ).
# Việc này có ý nghĩa hết sức thù thắng khi sử dụng socket, dùng để phần tải các client trên các server khác nhau cũng có thể giao tiếp với nhau thông qua redis.
- Mail: gửi mail dùng một layout phức tạp, gửi data từ controller xuống template mail.
- Midelware: Sử dụng cho trường hợp cần kiểm tra rule trước khi gọi vào một controller xác đinh.
- Security: cho việc đăng nhập lấy token, phân quyền các request có được thực hiện hay không.
- Phân trang các kết quả trả về client.

# Trong dự án Printgo có rất nhiều thứ nhưng tube này chủ yếu dành cho các bạn mới bắt đầu để làm quen với Java Spring.
Các bạn quan tâm nhiều hơn về đa luồng, bất đồng bộ, promise, non blocking vui lòng email cho mình tại huu.long.100@gmail.com.

# java -jar -Dlogging.config=conf/log4j2.xml Run.jar
