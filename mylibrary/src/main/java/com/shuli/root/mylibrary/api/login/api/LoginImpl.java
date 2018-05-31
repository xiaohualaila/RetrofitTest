/*
 * Copyright 2017 GcsSloop
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Last modified 2017-03-08 01:01:18
 *
 * GitHub:  https://github.com/GcsSloop
 * Website: http://www.gcssloop.com
 * Weibo:   http://weibo.com/GcsSloop
 */

package com.shuli.root.mylibrary.api.login.api;

import android.content.Context;
import android.support.annotation.NonNull;
import com.shuli.root.mylibrary.api.base.bean.OAuth;
import com.shuli.root.mylibrary.api.base.callback.TokenCallback;
import com.shuli.root.mylibrary.api.base.impl.BaseImpl;
import com.shuli.root.mylibrary.api.login.event.LoginEvent;
import com.shuli.root.mylibrary.api.login.event.LogoutEvent;
import com.shuli.root.mylibrary.utils.UUIDGenerator;
import org.greenrobot.eventbus.EventBus;

public class LoginImpl extends BaseImpl<LoginService> implements LoginAPI {

    public LoginImpl(@NonNull Context context) {
        super(context);
    }

    /**
     * 登录时调用
     * 返回一个 token，用于获取各类私有信息使用，该 token 用 LoginEvent 接收。
     *
     * @param user_name 用户名
     * @param password  密码
     * @see LoginEvent
     */
    @Override
    public String login(@NonNull String user_name, @NonNull String password) {
        final String uuid = UUIDGenerator.getUUID();
        mService.getToken(OAuth.client_id, OAuth.client_secret, OAuth.GRANT_TYPE_LOGIN, user_name, password)
                .enqueue(new TokenCallback(mCacheUtil, new LoginEvent(uuid)));
        return uuid;
    }

    /**
     * 用户登出
     */
    @Override
    public void logout() {
        String uuid = UUIDGenerator.getUUID();
        mCacheUtil.clearToken();    // 清除token
        EventBus.getDefault().post(new LogoutEvent(uuid, 0, "用户登出"));
    }

    /**
     * 是否登录
     *
     * @return 是否登录
     */
    @Override
    public boolean isLogin() {
        return !(mCacheUtil.getToken() == null);
    }




}
