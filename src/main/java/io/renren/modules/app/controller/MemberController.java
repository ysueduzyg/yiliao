package io.renren.modules.app.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.renren.common.annotation.SysLog;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.modules.app.form.MemberAddForm;
import io.renren.modules.app.form.MemberForm;
import io.renren.modules.doctor.entity.EnterpriseEntity;
import io.renren.modules.member.entity.*;
import io.renren.modules.member.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 会员信息
 *
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:23
 */
@RestController
@RequestMapping("bk/members")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberChildService memberChildService;
    @Autowired
    private MemberHistoryDiseaseService diseaseService;
    @Autowired
    private MemberCurrentService currentService;
    @Autowired
    private MemberGenenticDiseaseService genenticDiseaseService;
    @Autowired
    private MemberAllergyService allergyService;
    @Autowired
    private MemberCheckService checkService;
    @Autowired
    private MemberDrugService drugService;
    @Autowired
    private MemberLifeService lifeService;
    private Logger logger = LoggerFactory.getLogger(MemberController.class);

    /**
     * 列表
     */
    @RequestMapping("/list")
    @SysLog("查看会员列表")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = memberService.queryPage(params);
        return R.ok().put("page", page);
    }





}


