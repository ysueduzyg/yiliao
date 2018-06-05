package io.renren.modules.user.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.exception.RRException;
import io.renren.common.validator.Assert;
import io.renren.modules.app.form.LoginForm;
import io.renren.modules.doctor.entity.DoctorEntity;
import io.renren.modules.doctor.service.CommunityService;
import io.renren.modules.doctor.service.DoctorService;
import io.renren.modules.doctor.service.EnterpriseService;
import io.renren.modules.member.entity.MemberEntity;
import io.renren.modules.member.service.MemberChildService;
import io.renren.modules.member.service.MemberService;
import io.renren.modules.user.service.UserService;
import io.renren.modules.user.dao.UserDao;
import io.renren.modules.user.entity.UserEntity;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberChildService childService;
    @Autowired
    private CommunityService communityService;
    @Autowired
    private EnterpriseService enterpriseService;

    @Override
    public UserEntity queryByMobile(String mobile) {
        UserEntity userEntity = new UserEntity();
        userEntity.setMobile(mobile);
        return baseMapper.selectOne(userEntity);
    }

    @Override
    public long login(LoginForm form) {
        UserEntity user = queryByMobile(form.getMobile());
        Assert.isNull(user, "手机号或密码错误");
        //密码错误
        String userPwd = user.getPassword();
        String formPwd = new Sha256Hash(form.getPassword(), user.getSalt()).toHex();
        if (!userPwd.equals(formPwd)) {
            throw new RRException("手机号或密码错误");
        }

        return user.getUserId();
    }

    @Override
    public MemberEntity loginByMember(LoginForm form) {
        UserEntity user = queryByMobile(form.getMobile());
        Assert.isNull(user, "手机号或密码错误");
        //密码错误
        String userPwd = user.getPassword();
        String formPwd = new Sha256Hash(form.getPassword(), user.getSalt()).toHex();
        if (!userPwd.equals(formPwd)) {
            throw new RRException("手机号或密码错误");
        }
        Wrapper<MemberEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", user.getUserId());
        MemberEntity memberEntity = memberService.selectOne(wrapper);
        Assert.isNull(memberEntity, "会员不存在");
        return memberEntity;
    }

    @Override
    public DoctorEntity loginByDoctor(LoginForm form) {

        UserEntity user = queryByMobile(form.getMobile());
        Assert.isNull(user, "手机号或密码错误");
        //密码错误
        String userPwd = user.getPassword();
        String formPwd = new Sha256Hash(form.getPassword(), user.getSalt()).toHex();
        if (!userPwd.equals(formPwd)) {
            throw new RRException("手机号或密码错误");
        }
        Wrapper<DoctorEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", user.getUserId());
        DoctorEntity doctorEntity = doctorService.selectOne(wrapper);
        Assert.isNull(doctorEntity, "医生不存在");
        return doctorEntity;
    }
}
