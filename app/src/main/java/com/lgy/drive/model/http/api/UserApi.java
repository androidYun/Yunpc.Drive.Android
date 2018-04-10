package com.lgy.drive.model.http.api;

import com.lgy.drive.model.http.req.RecordReq;

import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ${lgy} on 2018/3/1210:24
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public interface UserApi {

    /**
     * 首页
     *
     * @return
     */
    @POST("drive/login")
    Observable<String> doLogin(@Query("phone") String phone, @Query("password") String password);


    /**
     * 首页
     *
     * @return
     */
    @POST("drive/register")
    Observable<String> doRegister(@Query("phone") String phone, @Query("password") String password);


    /**
     * 首页
     *
     * @return
     */
    @GET("user/recordlist")
    Observable<String> getPinCarList(@Query("userid") int userid);

    /**
     * 预约车辆
     *
     * @return
     */
    @GET("user/appointment")
    Observable<String> appointmentCar(@Query("userid") int userid, @Query("recordId") int recordId);


    /**
     * 获取所有订单
     *
     * @return
     */
    @GET("user/allOrder")
    Observable<String> getAllOrder(@Query("userId") int userid, @Query("pageNow") int pageNow, @Query("pageSize") int pageSize);


    /**
     * 忘记密码
     *
     * @return
     */
    @GET("user/modifyPwd")
    Observable<String> doForgetPwd(@Query("userId") int userid, @Query("oldPwd") String oldPwd, @Query("newPwd") String newPwd);


    /**
     * 完善个人资料
     *
     * @return
     */
    @POST("drive/completeUserInfor")
    Observable<String> completeUserInfor(@Query("name") String name, @Query("sex") String sex, @Query("idcardno") String idcardno, @Query("driveno") String driveno, @Query("getlicensetime") String getlicensetime, @Query("idcardheadurl") String idcardheadurl, @Query("drivehead") String drivehead);

    /**
     * 完善车辆信息资料
     *
     * @return
     */
    @POST("drive/completeCarInfor")
    Observable<String> completeCarInfor(@Query("drivelicenseurl") String drivelicenseurl, @Query("carname") String carname, @Query("carnumber") String carnumber);


    @POST("/images")
    @Multipart
    Observable<String> uploadImages(@Part MultipartBody.Part file);


    @POST("record/add")
    Observable<String> addRecord(@Body RecordReq recordReq);


    @POST("order/all/{state}")
    Observable<String> getOrderList(@Path("state") int state,@Query("pageNumber") int pageNumber,@Query("pageSize") int pageSize);

    @PUT("order/{state}")
    Observable<String> handlerOrder(@Path("state") int state, @Query("orderId") int orderId);
}
