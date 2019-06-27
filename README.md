# TIKI HOME TEST
Đây là source dành cho bài test Android tại nhà của Tiki.

## Mô tả bài toán

Yêu cầu của bài toán là hiển thị danh sách keyword trên một list dạng nằm ngang. Đối với keyword nào có từ 2 từ trở lên thì sẽ hiển thị dưới dạng 2 dòng với số chữ khác nhau giữ 2 dòng là nhỏ nhất.

## Giải quyết bài toán

Do yêu cầu bài toán là xử lý cho các keyword có từ 2 từ trở lên nên ta có thể chia thành 2 trường hợp để xử lý như sau(đã xử lý khoảng trắng đầu cuối cho keyword):

* **Trường hợp 1**: Keyword được cho vào xử lý là rỗng hoặc chỉ có một từ -> trường hợp này ta sẽ return về chính keyword đó.
* **Trường hợp 2**: Keyword được cho vào xử lý có 2 từ -> trường hợp này ta cần áp dụng thuật toán như sau:
  * Đánh index vị trí chèn \n là p = 0, giá trị khác nhau nhỏ nhất giữa 2 dòng min = 1000 (Chọn 1000 vì keyword thường là một chuỗi từ khá ngắn nên khó có thể vượt qua mức này được)
  * Duyệt chuỗi (s) từ **i = 0 ... độ dài chuỗi (s.length)**
    * Xét chuỗi vị trí chuỗi tại s[i], có 2 trường hợp:
      * Nếu **s[i] = ' '** thì:
        * Tính độ dài chuỗi từ 0 đến i: **l1 = i**
        * Tính độ dài còn lại: **l2 = s.length - i - 1**
        * Tính giá trị khác nhau giữa 2 chuỗi: **diff = Math.abs(l2 - l1)** (Dùng abs vì có thể xảy ra trường hợp l1 dài hơn l2)
          * Nếu **diff <= min** thì: 
            * Gán **p = i**
            * Nếu **diff = 0** thì dừng vòng lặp (Giá trị khác nhau giữa 2 dòng ta có thể tìm được thấp nhất là 0, khi bằng 0 nghĩa là 2 dòng đã bằng với nhau)
            * Ngược lại nếu diff > 0 thì gán: **min = diff**
            * Tiếp tục duyệt cho đến hết độ dài chuỗi
      * Nếu **s[i] != ' '** thì tiếp tục duyệt cho đến hết độ dài chuỗi
  * Gán vị trí chuỗi thứ p: **s[p] = "\n"**
  * Trả về chuỗi sau khi xử lý

Như vậy sau khi sử dụng thuật toán trên, ta sẽ nhận được chuỗi đã chèn **'\n'** tại vị trí cần tìm để tách chuỗi keyword thành 2 dòng theo yêu cầu đã cho.

## Xây dựng ứng dụng

* Khởi tạo project: Tạo một project mới trên Android Studio
* Thêm các thư viện cần thiết:
  * **RecyclerView**: Đây là một widget dùng để hiển thị view dưới dạng List. Vì yêu cầu là hiển thị danh sách keyword nên có thể chọn widget này
    ```
    implementation 'com.android.support:recyclerview-v7:28.0.0'
    implementation 'com.android.support:design:28.0.0'
    implementation 'com.android.support:cardview-v7:28.0.0'
    ```
  * **RxJava/RxAndroid**: Thư viện xử lý cho reactive program. Dùng để kết hợp với Retrofit xử lý API
    ```
    implementation 'io.reactivex.rxjava2:rxandroid:2.0.2'
    implementation 'io.reactivex.rxjava2:rxjava:2.1.12'
    ```
  * **Retrofit**: Dùng thư viện này để xử lý API đến server để lấy danh sách keyword, có thể dùng cách thông thường thông qua HttpConnection, OkHttp nhưng khi áp dụng Retrofit sẽ dễ dàng triển khai hơn, code cũng tối ưu hơn
    ```
    implementation 'com.squareup.retrofit2:retrofit:2.4.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.4.0'
    implementation 'com.squareup.retrofit2:converter-scalars:2.3.0'
    implementation 'com.squareup.retrofit2:adapter-rxjava2:2.4.0'
    implementation 'com.squareup.okhttp3:logging-interceptor:3.10.0'
    ```
  * **Gson**: Dùng thư viện này để handle dữ liệu json từ server trả về, danh sách keyword trong đề bài là một json nên dùng thư viện này để xử lý cho nó
    ```
    implementation 'com.google.code.gson:gson:2.8.2'
    ```
  * **KPregressHUB**: Dùng thư viện này để hiện thị progress bar trong khi lấy dữ liệu từ server, tạo ra UX tốt cho người dùng.
    ```
    implementation 'com.kaopiz:kprogresshud:1.1.0'
    ```
* Xây dựng Project Struct:
Trong bài này lựa chọn Struct cho project là **MVP** (Model - View - Presenter). Đây là một mô hình khá quen thuộc trong Android, chọn mô hình này vì nó có các lợi ích sau:
  * Chia nhỏ các xử lý như vậy việc giải quyết các request lớn sẽ dễ dàng hơn
  * Code đơn giản, ngắn gọn hơn. Dễ dàng debug cho các xử lý được chia nhỏ theo từng phần nhỏ
  * Dễ dàng mở rộng project hơn khi có thêm các yêu cầu mới, do cấu trúc project đã được phân định rõ ràng.
* Tiến hành xây dựng các component cần tiết cho project, chi tiết có thể xem tại: [đây](https://github.com/dinhlamvn/tiki_home_test/tree/master/app/src/main)
