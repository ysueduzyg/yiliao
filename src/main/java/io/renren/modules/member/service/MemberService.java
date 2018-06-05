package io.renren.modules.member.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.app.form.MemberForm;
import io.renren.modules.doctor.entity.DoctorEntity;
import io.renren.modules.member.entity.MemberCommentsEntity;
import io.renren.modules.member.entity.MemberEntity;

import java.util.Map;

/**
 * 会员信息
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:23
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
    PageUtils queryByDoctorPage(Map<String, Object> params, DoctorEntity doctorEntity);
    PageUtils queryChildPage(Map<String, Object> params);
    void save (MemberForm memberForm);
    void updateComment(MemberEntity memberEntity, MemberCommentsEntity commentsEntity);

}

