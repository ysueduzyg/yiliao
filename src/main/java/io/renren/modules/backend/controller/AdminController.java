package io.renren.modules.backend.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.renren.common.annotation.SysLog;
import io.renren.common.annotation.UserLog;
import io.renren.common.exception.RRException;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.utils.RegexUtil;
import io.renren.modules.app.annotation.DoctorLogin;
import io.renren.modules.app.annotation.DoctorLoginUser;
import io.renren.modules.app.form.DoctorForm;
import io.renren.modules.app.form.HouseKeeperForm;
import io.renren.modules.app.form.MemberForm;
import io.renren.modules.backend.form.DoctorTaskForm;
import io.renren.modules.doctor.entity.*;
import io.renren.modules.doctor.service.*;
import io.renren.modules.member.entity.*;
import io.renren.modules.member.service.*;
import io.renren.modules.sys.entity.JPushSet;
import io.renren.modules.sys.entity.MsgContent;
import io.renren.modules.sys.service.JPushService;
import io.renren.modules.sys.service.MsgContentService;
import io.renren.modules.user.entity.UserEntity;
import io.renren.modules.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.models.Tag;
import io.swagger.models.auth.In;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bk")
@Api("总后台")
public class AdminController {
    @Autowired
    private CommunityService communityService;

    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private HouseKeeperService houseKeeperService;
    @Autowired
    private MemberLevelService levelService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberLifeService lifeService;
    @Autowired
    private MemberDrugService drugService;
    @Autowired
    private MemberCheckService checkService;
    @Autowired
    private MemberCurrentService currentService;
    @Autowired
    private MemberAllergyService allergyService;
    @Autowired
    private MemberHistoryDiseaseService diseaseService;
    @Autowired
    private MemberGenenticDiseaseService genenticDiseaseService;
    @Autowired
    private MemberChildService childService;
    @Autowired
    private UserService userService;
    @Autowired
    private DoctorLogService logService;
    @Autowired
    private MemberCommentsService commentsService;
    @Autowired
    private MemberTaskService taskService;
    @Autowired
    private MemberSurgeryService surgeryService;
    @Autowired
    private ContentHtmlService contentHtmlService;
    @Autowired
    private MsgContentService msgContentService;
    @Autowired
    private JPushService jPushService;
    @Autowired
    private TagsService tagsService;
    @Autowired
    private MemberTagService memberTagService;
    @Autowired
    private ContentTagTempService contentTagTempService;

    @SysLog("获取全部社区列表分页")
    @RequestMapping("/community/list")
    @RequiresPermissions("admin:community:list")
    public R communityList(@RequestParam Map<String, Object> params) {
        PageUtils page = communityService.queryPage(params);

        return R.ok().put("page", page);
    }

    @SysLog("获取社区的详细信息")
    @RequestMapping("/community/info/{id}")
    @RequiresPermissions("admin:community:info")
    public R communityInfo(@PathVariable("id") Integer id) {
        CommunityEntity Community = communityService.selectById(id);
        return R.ok().put("community", Community);
    }

    @SysLog("添加社区")
    @RequestMapping("/community/save")
    @RequiresPermissions("admin:community:save")
    public R communitySave(@RequestBody CommunityEntity community) {
        if (StringUtils.isBlank(community.getCommunityManagerMobile())) {
            return R.error(401, "请输入管理员的手机号码");
        }
        if (!RegexUtil.isMobile(community.getCommunityManagerMobile())) {
            return R.error(402, "请输入正确的手机号码");
        }
        if (StringUtils.isBlank(community.getCommunityManagerName())) {
            return R.error(401, "请输入管理员姓名");
        }
        if (StringUtils.isBlank(community.getImg())) {
            return R.error(401, "请上传社区图片");
        }
        if (StringUtils.isBlank(community.getCommunityManagerName())) {
            return R.error(401, "请输入社区名称");
        }
        Wrapper<UserEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("mobile", community.getCommunityManagerMobile());
        List<UserEntity> list = userService.selectList(wrapper);
        if (list.size() > 0) {
            return R.error(401, "手机号码重复");
        }
        communityService.save(community);
        return R.ok();
    }


    @SysLog("修改社区")
    @RequestMapping("/community/update")
    @RequiresPermissions("admin:community:update")
    public R communityUpdate(@RequestBody CommunityEntity communityEntity) {
        communityEntity.setGmtModified(new Date());
        communityService.updateById(communityEntity);
        return R.ok();
    }


    @SysLog("删除社区")
    @RequestMapping("/community/delete")
    @RequiresPermissions("admin:community:delete")
    public R communityDelete(@RequestBody Integer[] ids) {
        CommunityEntity communityEntity = new CommunityEntity();
        communityEntity.setIsDeleted(Constant.DeleteType.is_deleted.getValue());
        Wrapper<CommunityEntity> wrapper = new EntityWrapper<>();
        wrapper.in("id", Arrays.asList(ids));
        communityService.update(communityEntity, wrapper);

        Wrapper<CommunityEntity> communityEntityWrapper = new EntityWrapper<>();
        communityEntityWrapper.in("id", Arrays.asList(ids));
        List<CommunityEntity> list = communityService.selectList(communityEntityWrapper);
        List<Integer> uids = list.stream()
                .map(CommunityEntity::getCommunityManagerId)
                .collect(Collectors.toList());

        Wrapper<UserEntity> userWrapper = new EntityWrapper<>();
        userWrapper.in("user_id", uids);
        UserEntity userEntity = new UserEntity();
        userEntity.setIsDeleted(Constant.DeleteType.is_deleted.getValue());
        userService.update(userEntity, userWrapper);

        return R.ok();
    }


    @SysLog("全部企业列表分页")
    @RequestMapping("/enterprise/list")
    @RequiresPermissions("admin:enterprise:list")
    public R enterpriseList(@RequestParam Map<String, Object> params) {
        PageUtils page = enterpriseService.queryPage(params);

        return R.ok().put("page", page);
    }


    @SysLog("企业详细信息")
    @RequestMapping("/enterprise/info/{id}")
    @RequiresPermissions("admin:enterprise:info")
    public R enterpriseInfo(@PathVariable("id") Integer id) {
        EnterpriseEntity enterprise = enterpriseService.selectById(id);

        return R.ok().put("enterprise", enterprise);
    }


    @SysLog("保存企业信息")
    @RequestMapping("/enterprise/save")
    @RequiresPermissions("admin:enterprise:save")
    public R enterpriseSave(@RequestBody EnterpriseEntity enterprise) {
        if (StringUtils.isBlank(enterprise.getEnterpriseManagerMobile())) {
            return R.error(401, "请输入管理员的手机号码");
        }
        if (!RegexUtil.isMobile(enterprise.getEnterpriseManagerMobile())) {
            return R.error(402, "请输入正确的手机号码");
        }
        if (StringUtils.isBlank(enterprise.getEnterpriseManagerName())) {
            return R.error(401, "请输入管理员姓名");
        }
        if (StringUtils.isBlank(enterprise.getImg())) {
            return R.error(401, "请上传社区图片");
        }
        if (StringUtils.isBlank(enterprise.getName())) {
            return R.error(401, "请输入社区名称");
        }
        enterpriseService.save(enterprise);

        return R.ok();
    }


    @SysLog("更新企业信息")
    @RequestMapping("/enterprise/update")
    @RequiresPermissions("admin:enterprise:update")
    public R enterpriseUpdate(@RequestBody EnterpriseEntity enterprise) {
        enterpriseService.updateById(enterprise);

        return R.ok();
    }


    @SysLog("删除企业信息")
    @RequestMapping("/enterprise/delete")
    @RequiresPermissions("admin:enterprise:delete")
    public R enterpriseDelete(@RequestBody Integer[] ids) {
        EnterpriseEntity entity = new EnterpriseEntity();
        entity.setIsDeleted(Constant.DeleteType.is_deleted.getValue());
        Wrapper<EnterpriseEntity> wrapper = new EntityWrapper<>();
        wrapper.in("id", Arrays.asList(ids));
        enterpriseService.update(entity, wrapper);


        Wrapper<EnterpriseEntity> enterpriseEntityEntityWrapper = new EntityWrapper<>();
        enterpriseEntityEntityWrapper.in("id", ids);
        List<EnterpriseEntity> doctorEntities = enterpriseService.selectList(enterpriseEntityEntityWrapper);
        List<Integer> uids = doctorEntities.stream()
                .map(EnterpriseEntity::getEnterpriseManagerId)
                .collect(Collectors.toList());

        Wrapper<UserEntity> userWrapper = new EntityWrapper<>();
        userWrapper.in("user_id", uids);

        UserEntity userEntity = new UserEntity();
        userEntity.setIsDeleted(Constant.DeleteType.is_deleted.getValue());
        userService.update(userEntity, userWrapper);
        return R.ok();
    }


    @SysLog("全部医生列表分页")
    @RequestMapping("/doctor/list")
    @RequiresPermissions("admin:doctor:list")
    public R doctorList(@RequestParam Map<String, Object> params) {
        PageUtils page = doctorService.queryPage(params);

        return R.ok().put("page", page);
    }


    @SysLog("获取医生详细信息")
    @RequestMapping("/doctor/info/{id}")
    @RequiresPermissions("admin:doctor:update")
    public R doctorInfo(@PathVariable("id") Integer id) {
        DoctorForm doctor = doctorService.info(id);

        return R.ok().put("doctor", doctor);
    }


    @SysLog("添加医生")
    @RequestMapping("/doctor/save")
    @RequiresPermissions("admin:doctor:save")
    public R doctorSave(@RequestBody DoctorForm form) {
        if (StringUtils.isBlank(form.getMobile())) {
            return R.error(401, "请输入医生的手机号码");
        }
        if (!RegexUtil.isMobile(form.getMobile())) {
            return R.error(402, "请输入正确的手机号码");
        }
        doctorService.save(form);

        return R.ok();
    }


    @SysLog("修改医生")
    @RequestMapping("/doctor/update")
    @RequiresPermissions("admin:doctor:update")
    public R doctorUpdate(@RequestBody DoctorForm form) {
        doctorService.update(form);
        return R.ok();
    }


    @SysLog("删除医生")
    @RequestMapping("/doctor/delete")
    @RequiresPermissions("admin:doctor:delete")
    public R doctorDelete(@RequestBody Integer[] ids) {
        doctorService.deleteDoctor(Arrays.asList(ids));
        return R.ok();
    }


    @SysLog("会员等级列表分页")
    @RequestMapping("/level/list")
    @RequiresPermissions("admin:level:list")
    public R levelList(@RequestParam Map<String, Object> params) {
        PageUtils page = levelService.queryPage(params);

        return R.ok().put("page", page);
    }


    @SysLog("会员等级信息")
    @RequestMapping("/level/info/{id}")
    public R levelInfo(@PathVariable("id") Integer id) {
        MemberLevelEntity tblMemberLevel = levelService.selectById(id);
        return R.ok().put("level", tblMemberLevel);
    }


    @SysLog("会员等级添加")
    @RequestMapping("/level/save")
    @RequiresPermissions("admin:level:save")
    public R levelSave(@RequestBody MemberLevelEntity tblMemberLevel) {
        levelService.insert(tblMemberLevel);

        return R.ok();
    }


    @SysLog("会员等级修改")
    @RequestMapping("/level/update")
    @RequiresPermissions("admin:level:update")
    public R levelUpdate(@RequestBody MemberLevelEntity tblMemberLevel) {
        tblMemberLevel.setGmtModified(new Date());
        levelService.updateById(tblMemberLevel);
        return R.ok();
    }


    @SysLog("会员等级删除")
    @RequestMapping("/level/delete")
    @RequiresPermissions("admin:level:delete")
    public R levelDelete(@RequestBody Integer[] ids) {
        Wrapper<MemberLevelEntity> wrapper = new EntityWrapper<>();
        wrapper.in("id", Arrays.asList(ids));
        MemberLevelEntity levelEntity = new MemberLevelEntity();
        levelEntity.setIsDeleted(Constant.DeleteType.is_deleted.getValue());
        levelEntity.setGmtModified(new Date());
        levelService.update(levelEntity, wrapper);
        return R.ok();
    }


    @SysLog("查询社区下所有的医生")
    @GetMapping("/communityDoctorsById/{id}")
    public R communityDoctorsById(@PathVariable Integer id) {
        Wrapper<DoctorEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("community_id", id).eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        return R.ok().put("doctors", doctorService.selectList(wrapper));
    }


    @SysLog("查询企业下所有医生")
    @GetMapping("/enterpriseDoctorsById/{id}")
    public R enterpriseDoctorsById(@PathVariable Integer id) {
        Wrapper<DoctorEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("enterprise_id", id).eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        return R.ok().put("doctors", doctorService.selectList(wrapper));
    }


    @SysLog("查询社区下所有的管家")
    @GetMapping("/communityHouseKeeperById/{id}")
    public R communityHouseKeeperById(@PathVariable Integer id) {
        Wrapper<HouseKeeperEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("community_id", id).eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        return R.ok().put("houseKeepers", houseKeeperService.selectList(wrapper));
    }

    @SysLog("查询企业下所有的管家")
    @GetMapping("/enterpriseHouseKeeperById/{id}")
    public R enterpriseHouseKeeperById(@PathVariable Integer id) {
        Wrapper<HouseKeeperEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("enterprise_id", id).eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        return R.ok().put("houseKeepers", houseKeeperService.selectList(wrapper));
    }


    @GetMapping("/communities")
    @SysLog("获取所有的社区")
    public R communities() {
        Wrapper<CommunityEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        return R.ok().put("communities", communityService.selectList(wrapper));
    }

    @GetMapping("/enterprises")
    @SysLog("获取所有的企业")
    public R enterprises() {
        Wrapper<EnterpriseEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        return R.ok().put("enterprises", enterpriseService.selectList(wrapper));
    }

    @GetMapping("/levels")
    @SysLog("获取所有会员等级")
    public R levels() {
        Wrapper<MemberLevelEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("is_deleted", Constant.DeleteType.un_deleted.getValue()).orderBy("sort");
        List<MemberLevelEntity> list = levelService.selectList(wrapper);
        return R.ok().put("list", list);
    }

    @SysLog("查看会员列表分页")
    @RequestMapping("/member/list")
    public R memberList(@RequestParam Map<String, Object> params) {
        PageUtils page = memberService.queryPage(params);
        return R.ok().put("page", page);
    }

    @RequestMapping("/member/save")
    @SysLog("添加会员")
    public R memberSave(@RequestBody MemberForm memberForm) {

        if (StringUtils.isBlank(memberForm.getMobile())) {
            return R.error(401, "请输入会员的手机号码");
        }
        if (!RegexUtil.isMobile(memberForm.getMobile())) {
            return R.error(402, "请输入正确的手机号码");
        }
        Wrapper<UserEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("mobile", memberForm.getMobile());
        List<UserEntity> list = userService.selectList(wrapper);
        if (list.size() > 0) {
            return R.error("手机号码重复");
        }
        memberService.save(memberForm);
        return R.ok();
    }


    @RequestMapping("/member/update")
    @SysLog("修改会员信息")
    public R updateMember(@RequestBody MemberEntity tblMember) {
        memberService.updateById(tblMember);
        return R.ok();
    }

    @RequestMapping("/member/delete")
    @SysLog("删除会员")
    public R delete(@RequestBody Integer[] ids) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setIsDeleted(Constant.DeleteType.is_deleted.getValue());
        Wrapper<MemberEntity> wrapper = new EntityWrapper<>();
        wrapper.in("id", Arrays.asList(ids));
        memberService.update(memberEntity, wrapper);
        return R.ok();
    }

    @RequestMapping("/member/info/{id}")
    @SysLog("会员详情")
    public R info(@PathVariable("id") Integer id) {
        MemberEntity memberEntity = memberService.selectById(id);
        return R.ok().put("member", memberEntity);
    }


    @GetMapping("/member/sn/{sn}")
    @SysLog("会员编号联想")
    public R list(@PathVariable("sn") String sn) {
        Wrapper<MemberEntity> wrapper = new EntityWrapper<>();
        wrapper.like("sn", sn).eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        return R.ok().put("list", memberService.selectList(wrapper));
    }

    @SysLog("会员联系人")
    @RequestMapping("/member/child-list")
    public R childList(@RequestParam Map<String, Object> params) {
        PageUtils page = memberService.queryChildPage(params);
        return R.ok().put("page", page);
    }


    @SysLog("会员联系人信息")
    @RequestMapping("/member/info-child/{id}")
    public R chidInfo(@PathVariable("id") Integer id) {
        MemberChildEntity childEntity = childService.selectById(id);
        return R.ok().put("child", childEntity);
    }

    @SysLog("会员联系人添加")
    @RequestMapping("/member/add-child")
    public R saveChild(@RequestBody MemberChildEntity childEntity) {
        childService.save(childEntity);
        return R.ok();
    }

    @SysLog("会员联系人信息修改")
    @RequestMapping("/member/update-child")
    public R updateChild(@RequestBody MemberChildEntity childEntity) {
        childService.updateById(childEntity);

        return R.ok();
    }

    @SysLog("会员联系人信息删除")
    @RequestMapping("/member/delete-child")
    public R deleteChild(@RequestBody Integer[] ids) {
        for (int i = 0; i < ids.length; i++) {
            Wrapper<MemberChildEntity> wrapper = new EntityWrapper<>();
            wrapper.eq("id", ids[0]);
            MemberChildEntity childEntity = new MemberChildEntity();
            childEntity.setIsDeleted(Constant.DeleteType.is_deleted.getValue());
            childService.update(childEntity, wrapper);
        }
        return R.ok();
    }


    @RequestMapping("/member/save-life")
    @SysLog("保存兴趣爱好")
    public R save(@RequestBody MemberLifeEntity lifeEntity) {
        Integer id = lifeEntity.getId();
        MemberLifeEntity lifeEntity1 = lifeService.selectById(id);
        if (lifeEntity1 != null) {
            lifeService.updateById(lifeEntity);
        } else {
            lifeService.insert(lifeEntity);
        }
        return R.ok();
    }

    @SysLog("查看兴趣爱好")
    @RequestMapping("/member/info-life/{id}")
    public R lifeInfo(@PathVariable("id") Integer id) {
        Wrapper<MemberLifeEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("member_id", id).eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        MemberLifeEntity lifeEntity = lifeService.selectOne(wrapper);
        return R.ok().put("life", lifeEntity);
    }


    @SysLog("添加用药记录")
    @RequestMapping("/member/add-drug")
    public R saveDrug(@RequestBody MemberDrugEntity drugEntity) {
        drugService.insert(drugEntity);
        return R.ok();
    }


    @RequestMapping("/member/info-drug/{id}")
    @SysLog("查看用药信息")
    public R drugInfo(@PathVariable("id") Integer id) {
        MemberDrugEntity drugEntity = drugService.selectById(id);
        return R.ok().put("drug", drugEntity);
    }


    @RequestMapping("/member/update-drug")
    @SysLog("修改用药记录")
    public R updateDrug(@RequestBody MemberDrugEntity drugEntity) {
        drugService.updateById(drugEntity);
        return R.ok();
    }

    @RequestMapping("/member/delete-drug")
    @SysLog("删除会员用药")
    public R deleteDrug(@RequestBody Integer[] ids) {
        MemberDrugEntity drugEntity = new MemberDrugEntity();
        drugEntity.setIsDeleted(Constant.DeleteType.is_deleted.getValue());
        Wrapper<MemberDrugEntity> wrapper = new EntityWrapper<>();
        wrapper.in("id", Arrays.asList(ids));
        drugService.update(drugEntity, wrapper);
        return R.ok();
    }

    @SysLog("会员用药列表分页")
    @RequestMapping("/member/drug-list")
    public R drugList(@RequestParam Map<String, Object> params) {
        PageUtils page = drugService.queryPage(params);
        return R.ok().put("page", page);
    }


    @SysLog("修改检查信息")
    @RequestMapping("/member/update-check")
    public R updateCheck(@RequestBody MemberCheckEntity checkEntity) {
        checkService.updateById(checkEntity);
        return R.ok();
    }

    @SysLog("查看检查信息")
    @RequestMapping("/member/info-check/{id}")
    public R checkInfo(@PathVariable("id") Integer id) {
        MemberCheckEntity checkEntity = checkService.selectById(id);
        return R.ok().put("check", checkEntity);
    }

    @SysLog("添加检查信息")
    @RequestMapping("/member/add-check")
    public R saveCheck(@RequestBody MemberCheckEntity checkEntity) {
        checkEntity.setIsDeleted(Constant.DeleteType.un_deleted.getValue());
        checkService.insert(checkEntity);
        return R.ok();
    }

    @SysLog("会员检查列表分页")
    @RequestMapping("/member/check-list")
    public R checkList(@RequestParam Map<String, Object> params) {
        PageUtils page = checkService.queryPage(params);
        return R.ok().put("page", page);
    }

    @RequestMapping("/member/delete-check")
    @SysLog("删除会员检查")
    public R deleteCheck(@RequestBody Integer[] ids) {
        MemberCheckEntity checkEntity = new MemberCheckEntity();
        checkEntity.setIsDeleted(Constant.DeleteType.is_deleted.getValue());
        Wrapper<MemberCheckEntity> wrapper = new EntityWrapper<>();
        wrapper.in("id", Arrays.asList(ids));
        checkService.update(checkEntity, wrapper);
        return R.ok();
    }


    @SysLog("过敏列表分页")
    @RequestMapping("/member/allergy-disease")
    public R allergySymptom(@RequestParam Map<String, Object> params) {
        PageUtils page = allergyService.queryPage(params);
        return R.ok().put("page", page);
    }

    @SysLog("修改过敏信息")
    @RequestMapping("/member/update-allergy-disease")
    public R updateAllergyDisease(@RequestBody MemberAllergyEntity allergyEntity) {
        if (allergyEntity.getMemberId() == null) {
            return R.error(404, "未找到会员信息");
        }
        allergyService.updateById(allergyEntity);
        return R.ok();
    }

    @SysLog("删除过敏信息")
    @RequestMapping("/member/delete-allergy-disease")
    public R deleteAllergyDisease(@RequestBody MemberAllergyEntity allergyEntity) {
        allergyEntity.setIsDeleted(Constant.DeleteType.is_deleted.getValue());
        allergyService.updateById(allergyEntity);
        return R.ok();
    }

    @SysLog("会员过敏信息")
    @RequestMapping("/member/save-allergy-disease")
    public R saveAllergyDisease(@RequestBody MemberAllergyEntity allergyEntity) {
        if (allergyEntity.getMemberId() == null) {
            return R.error(404, "未找到会员信息");
        }
        allergyEntity.setIsDeleted(Constant.DeleteType.un_deleted.getValue());
        allergyService.insert(allergyEntity);
        return R.ok();
    }

    @SysLog("添加会员遗传病史")
    @RequestMapping("/member/save-genentic-disease")
    public R saveGenenticDisease(@RequestBody MemberGenenticDiseaseEntity genenticDiseaseEntity) {
        if (genenticDiseaseEntity.getMemberId() == null) {
            return R.error(404, "未找到会员信息");
        }
        genenticDiseaseEntity.setIsDeleted(Constant.DeleteType.un_deleted.getValue());
        genenticDiseaseService.insert(genenticDiseaseEntity);
        return R.ok();
    }

    @SysLog("删除会员遗传病史")
    @RequestMapping("/member/delete-genentic-disease")
    public R deleteGenenticDisease(@RequestBody MemberGenenticDiseaseEntity genenticDisease) {
        genenticDisease.setIsDeleted(Constant.DeleteType.is_deleted.getValue());
        genenticDiseaseService.updateById(genenticDisease);
        return R.ok();
    }


    @SysLog("修改会员遗传病史")
    @RequestMapping("/member/update-genentic-disease")
    public R updateGenenticDisease(@RequestBody MemberGenenticDiseaseEntity genenticDiseaseEntity) {
        if (genenticDiseaseEntity.getMemberId() == null) {
            return R.error(404, "未找到会员信息");
        }
        genenticDiseaseService.updateById(genenticDiseaseEntity);
        return R.ok();
    }

    @SysLog("会员遗传病史列表分页")
    @RequestMapping("/member/genentic-disease")
    public R genenticSymptom(@RequestParam Map<String, Object> params) {
        PageUtils page = genenticDiseaseService.queryPage(params);
        return R.ok().put("page", page);
    }

    //历史
    @SysLog("会员病史列表分页")
    @RequestMapping("/member/history-disease")
    public R historySymptom(@RequestParam Map<String, Object> params) {
        PageUtils page = diseaseService.queryPage(params);
        return R.ok().put("page", page);
    }

    @SysLog("添加会员病史")
    @RequestMapping("/member/save-history-disease")
    public R saveHistoryDisease(@RequestBody MemberHistoryDiseaseEntity historyDiseaseEntity) {
        if (historyDiseaseEntity.getMemberId() == null) {
            return R.error(404, "未找到会员信息");
        }
        historyDiseaseEntity.setIsDeleted(Constant.DeleteType.un_deleted.getValue());
        diseaseService.insert(historyDiseaseEntity);
        return R.ok();
    }

    @SysLog("修改会员病史")
    @RequestMapping("/member/update-history-disease")
    public R updateHistoryDisease(@RequestBody MemberHistoryDiseaseEntity historyDiseaseEntity) {
        if (historyDiseaseEntity.getMemberId() == null) {
            return R.error(404, "未找到会员信息");
        }
        diseaseService.updateById(historyDiseaseEntity);
        return R.ok();
    }

    @SysLog("删除会员病史")
    @RequestMapping("/member/delete-history-disease")
    public R deleteHistoryDisease(@RequestBody MemberHistoryDiseaseEntity historyDiseaseEntity) {
        historyDiseaseEntity.setIsDeleted(Constant.DeleteType.is_deleted.getValue());
        diseaseService.updateById(historyDiseaseEntity);
        return R.ok();
    }

    @GetMapping("/member/current-disease/{memberId}")
    @SysLog("会员当前状况")
    public R currentSymptom(@PathVariable Integer memberId) {
        Wrapper<MemberCurrentEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("member_id", Integer.valueOf(memberId)).eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        MemberCurrentEntity entity = currentService.selectOne(wrapper);
        if (entity == null) {
            return R.ok().put("disease", new MemberCurrentEntity());
        }
        return R.ok().put("disease", entity);
    }

    @RequestMapping("/member/save-current-disease")
    @SysLog("更新当前状况")
    public R saveCurrentSymptom(@RequestBody MemberCurrentEntity memberCurrentEntity) {
        Integer memberId = memberCurrentEntity.getMemberId();
        if (memberId == null || memberId == 0) {
            return R.error(404, "未找到会员信息");
        }
        Wrapper<MemberCurrentEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("member_id", memberId);
        MemberCurrentEntity old = currentService.selectOne(wrapper);
        if (old == null) {
            currentService.insert(memberCurrentEntity);
        } else {
            old.setCurrentDisease(memberCurrentEntity.getCurrentDisease());
            old.setCurrentQuestion(memberCurrentEntity.getCurrentQuestion());
            currentService.updateById(old);
        }
        return R.ok();
    }


    @RequestMapping("/log-list")
    @SysLog("护理日志")
    public R logList(@RequestParam Map<String, Object> params) {
        PageUtils page = logService.queryBackendPage(params);
        return R.ok().put("page", page);
    }


    @RequestMapping("/doctor/log-list")
    @SysLog("获取医生日志列表")
    public R logDoctorList(@RequestParam Map<String, Object> params) {
        String id = (String) params.get("id");
        if (StringUtils.isBlank(id)) {
            return R.error("医生id不存在");
        }
        PageUtils page = logService.queryBackendDoctorPage(params, Integer.valueOf(id));
        DoctorEntity doctorEntity = doctorService.selectById(Integer.valueOf(id));
        return R.ok().put("page", page).put("info", doctorEntity);
    }

    @RequestMapping("/houseKeeper/log-list")
    @SysLog("获取医生日志列表")
    public R logHouseKeeperList(@RequestParam Map<String, Object> params) {
        String id = (String) params.get("id");
        if (StringUtils.isBlank(id)) {
            return R.error("医生id404");
        }
        PageUtils page = logService.queryBackendDoctorPage(params, Integer.valueOf(id));
        HouseKeeperEntity houseKeeperEntity = houseKeeperService.selectById(Integer.valueOf(id));
        return R.ok().put("page", page).put("info", houseKeeperEntity);
    }


    @RequestMapping("/doctor/comments-list")
    @SysLog("获取医生的评价")
    public R doctorComments(@RequestParam Map<String, Object> params) {
        String id = (String) params.get("id");
        if (StringUtils.isBlank(id)) {
            return R.error("缺少医生id");
        }
        params.put("doctorId", Integer.valueOf(id));
        PageUtils page = commentsService.queryDoctorPage(params);
        return R.ok().put("page", page);
    }


    @RequestMapping("/houseKeeper/comments-list")
    @SysLog("获取管家的评价")
    public R houseKeeperComments(@RequestParam Map<String, Object> params) {
        String id = (String) params.get("id");
        if (StringUtils.isBlank(id)) {
            return R.error("缺少管家id");
        }
        params.put("houseKeeperId", Integer.valueOf(id));
        PageUtils page = commentsService.queryHouseKeeperPage(params);
        return R.ok().put("page", page);

    }

    @RequestMapping("sos-list")
    @SysLog("获取紧急求助列表")
    public R sosList(@RequestParam Map<String, Object> params) {
        params.put("type", Constant.TaskType.is_sos.getValue());
        PageUtils page = taskService.queryPage(params);
        return R.ok().put("page", page);
    }

    @RequestMapping("life-list")
    @SysLog("获取生活服务列表")
    public R lifeList(@RequestParam Map<String, Object> params) {
        params.put("type", Constant.TaskType.is_call_housekeeper.getValue());
        PageUtils page = taskService.queryPage(params);
        return R.ok().put("page", page);
    }

    @RequestMapping("msg-list")
    @SysLog("获取咨询列表")
    public R messages(@RequestParam Map<String, Object> params) {
        params.put("type", Constant.TaskType.is_message.getValue());
        PageUtils page = taskService.queryPage(params);
        return R.ok().put("page", page);
    }


    @RequestMapping("top-list")
    @SysLog("获取医生排行")
    public R topList(@RequestParam Map<String, Object> params) {
        Wrapper<DoctorEntity> doctorEntityWrapper = new EntityWrapper<>();
        Integer total = doctorService.selectCount(null);

        Page<DoctorTaskForm> taskFormPage = new Page<>();
        taskFormPage.setCurrent(Integer.valueOf((String) params.get("page")));
        taskFormPage.setTotal(total);
        taskFormPage.setSize(Integer.valueOf((String) params.get("limit")));
        return R.ok().put("page", taskFormPage);
    }


    @RequestMapping("houseKeeper/list")
    @SysLog("获取私人医生列表")
    public R houseKeeperList(@RequestParam Map<String, Object> params) {
        PageUtils page = houseKeeperService.queryPage(params);

        return R.ok().put("page", page);
    }


    @RequestMapping("houseKeeper/save")
    @SysLog("添加私人管家")
    public R saveHouseKeeper(@RequestBody HouseKeeperForm form) {
        if (StringUtils.isBlank(form.getMobile())) {
            return R.error(401, "请输入医生的手机号码");
        }
        if (!RegexUtil.isMobile(form.getMobile())) {
            return R.error(402, "请输入正确的手机号码");
        }

        Wrapper<UserEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("mobile", form.getMobile());
        List<UserEntity> list = userService.selectList(wrapper);
        if (list.size() > 0) {
            return R.error("手机号码重复");
        }
        houseKeeperService.save(form);
        return R.ok();
    }


    @SysLog("修改私人管家")
    @RequestMapping("/houseKeeper/update")
    public R houseKeeperUpdate(@RequestBody HouseKeeperForm form) {
        houseKeeperService.update(form);
        return R.ok();
    }


    @SysLog("删除管家")
    @RequestMapping("/houseKeeper/delete")
    public R houseKeeperDelete(@RequestBody Integer[] ids) {
        houseKeeperService.deleteHouseKeeper(Arrays.asList(ids));
        return R.ok();
    }


    @SysLog("获取管家详细信息")
    @RequestMapping("/houseKeeper/info/{id}")
    public R houseKeeperInfo(@PathVariable("id") Integer id) {
        HouseKeeperForm keeper = houseKeeperService.info(id);

        return R.ok().put("keeper", keeper);
    }


    //历史
    @SysLog("会员外伤手术史")
    @RequestMapping("/member/surgical-list")
    public R surgeryList(@RequestParam Map<String, Object> params) {
        PageUtils page = surgeryService.queryPage(params);
        return R.ok().put("page", page);
    }

    @SysLog("添加会员外伤手术史")
    @RequestMapping("/member/add-surgical")
    public R saveSurgery(@RequestBody MemberSurgery surgery) {
        if (surgery.getMemberId() == null) {
            return R.error(404, "未找到会员信息");
        }
        surgeryService.insert(surgery);
        return R.ok();
    }

    @SysLog("修改会员病史")
    @RequestMapping("/member/update-surgical")
    public R updateSurgical(@RequestBody MemberSurgery surgery) {
        if (surgery.getMemberId() == null) {
            return R.error(404, "未找到会员信息");
        }
        surgery.setGmtModified(new Date());
        surgeryService.updateById(surgery);
        return R.ok();
    }

    @SysLog("删除会员病史")
    @RequestMapping("/member/delete-surgical")
    public R deleteSurgical(@RequestBody MemberSurgery surgery) {
        surgery.setIsDeleted(Constant.DeleteType.is_deleted.getValue());
        surgery.setGmtModified(new Date());
        surgeryService.updateById(surgery);

        return R.ok();
    }

    @SysLog("推送文章列表")
    @RequestMapping("/message/list")
    public R messageList(@RequestParam Map<String, Object> params) {
        PageUtils page = contentHtmlService.queryPage(params);
        return R.ok().put("page", page);
    }


    @SysLog("获取文章详情")
    @RequestMapping("/message/info/{id}")
    public R messageInfo(@PathVariable("id") Integer id) {
        ContentHtmlEntity contentHtmlEntity = contentHtmlService.selectById(id);
        return R.ok().put("info", contentHtmlEntity);
    }


    @SysLog("修改文章")
    @RequestMapping("/message/update")
    public R messageUpdate(@RequestBody ContentHtmlEntity contentHtmlEntity) {
        contentHtmlEntity.setGmtModified(new Date());
        contentHtmlService.updateById(contentHtmlEntity);
        Wrapper<ContentTagTempEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("content_id", contentHtmlEntity.getId());
        contentTagTempService.delete(wrapper);

        String tags = contentHtmlEntity.getTags();
        List<Long> list = JSONObject.parseArray(tags, Long.class);

        this.saveTagTemp(list, contentHtmlEntity.getId());
        return R.ok();
    }

    private void saveTagTemp(List<Long> list, Long id) {
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                ContentTagTempEntity entity = new ContentTagTempEntity();
                entity.setContentId(id);
                entity.setTagId(list.get(i));
                contentTagTempService.insert(entity);
            }
        }
    }

    @SysLog("删除文章")
    @RequestMapping("/message/delete")
    public R messageDelete(@RequestBody Integer[] ids) {
        Wrapper<ContentHtmlEntity> wrapper = new EntityWrapper<>();
        wrapper.in("id", Arrays.asList(ids));
        ContentHtmlEntity contentHtmlEntity = new ContentHtmlEntity();
        contentHtmlEntity.setIsDeleted(Constant.DeleteType.is_deleted.getValue());
        contentHtmlEntity.setGmtModified(new Date());
        contentHtmlService.update(contentHtmlEntity, wrapper);
        return R.ok();
    }


    @SysLog("文章添加")
    @RequestMapping("/message/save")
    public R messageSave(@RequestBody ContentHtmlEntity contentHtmlEntity) {
        contentHtmlService.insert(contentHtmlEntity);
        String tags = contentHtmlEntity.getTags();
        List<Long> list = JSONObject.parseArray(tags, Long.class);
        this.saveTagTemp(list, contentHtmlEntity.getId());
        return R.ok();
    }


    @SysLog("推送列表")
    @RequestMapping("/jpush/list")
    public R jPushList(@RequestParam Map<String, Object> params) {
        PageUtils page = jPushService.queryPage(params);
        return R.ok().put("page", page);
    }


    @SysLog("获取推送详情")
    @RequestMapping("/jpush/info/{id}")
    public R jPushInfo(@PathVariable("id") Integer id) {
        JPushSet jPushSet = jPushService.selectById(id);
        return R.ok().put("info", jPushSet);
    }


    @SysLog("修改推送")
    @RequestMapping("/jpush/update")
    public R jPushUpdate(@RequestBody JPushSet jPushSet) {
        jPushSet.setGmtModified(new Date());
        jPushService.updateById(jPushSet);
        return R.ok();
    }


    @SysLog("删除推送")
    @RequestMapping("/jpush/delete")
    public R jPushDelete(@RequestBody Integer[] ids) {
        Wrapper<JPushSet> wrapper = new EntityWrapper<>();
        wrapper.in("id", Arrays.asList(ids));
        JPushSet jPushSet = new JPushSet();
        jPushSet.setIsDeleted(Constant.DeleteType.is_deleted.getValue());
        jPushSet.setGmtModified(new Date());
        jPushService.update(jPushSet, wrapper);
        return R.ok();
    }


    @SysLog("推送添加")
    @RequestMapping("/jpush/save")
    public R jPushSave(@RequestBody JPushSet jPushSet) {
        jPushService.insert(jPushSet);
        return R.ok();
    }


    @SysLog("推送的信息设置")
    @RequestMapping("/msgContent/list")
    public R msgContentList(@RequestParam Map<String, Object> params) {
        PageUtils page = msgContentService.queryPage(params);
        return R.ok().put("page", page);
    }


    @SysLog("获取推送信息详情")
    @RequestMapping("/msgContent/info/{id}")
    public R msgContentInfo(@PathVariable("id") Integer id) {
        MsgContent msgContent = msgContentService.selectById(id);
        return R.ok().put("info", msgContent);
    }


    @SysLog("修改推送信息设置")
    @RequestMapping("/msgContent/update")
    public R msgContentUpdate(@RequestBody MsgContent msgContent) {
        msgContent.setGmtModified(new Date());
        msgContentService.updateById(msgContent);
        return R.ok();
    }


    @SysLog("删除推送")
    @RequestMapping("/msgContent/delete")
    public R msgContentDelete(@RequestBody Integer[] ids) {
        Wrapper<MsgContent> wrapper = new EntityWrapper<>();
        wrapper.in("id", Arrays.asList(ids));
        MsgContent msgContent = new MsgContent();
        msgContent.setIsDeleted(Constant.DeleteType.is_deleted.getValue());
        msgContent.setGmtModified(new Date());
        msgContentService.update(msgContent, wrapper);
        return R.ok();
    }


    @SysLog("推送设置添加")
    @RequestMapping("/msgContent/save")
    public R msgContentSave(@RequestBody MsgContent msgContent) {
        msgContentService.insert(msgContent);
        return R.ok();
    }


    @SysLog("添加标签")
    @RequestMapping("/tag/save")
    public R msgContentSave(@RequestBody TagsEntity tagsEntity) {
        tagsService.insert(tagsEntity);
        return R.ok();
    }

    @SysLog("删除标签")
    @RequestMapping("/tag/delete")
    public R tagDelete(@RequestBody Integer[] ids) {
        Wrapper<TagsEntity> wrapper = new EntityWrapper<>();
        wrapper.in("id", Arrays.asList(ids));
        TagsEntity tagsEntity = new TagsEntity();
        tagsEntity.setIsDeleted(Constant.DeleteType.is_deleted.getValue());
        tagsEntity.setGmtModified(new Date());
        tagsService.update(tagsEntity, wrapper);
        return R.ok();
    }


    @SysLog("标签列表")
    @RequestMapping("/tag/list")
    public R tagList(@RequestParam Map<String, Object> params) {
        PageUtils page = tagsService.queryPage(params);
        return R.ok().put("page", page);
    }


    @SysLog("获取推送信息详情")
    @RequestMapping("/tag/info/{id}")
    public R tagInfo(@PathVariable("id") Integer id) {
        TagsEntity tagsEntity = tagsService.selectById(id);
        return R.ok().put("info", tagsEntity);
    }


    @SysLog("修改推送信息设置")
    @RequestMapping("/tag/update")
    public R tagUpdate(@RequestBody TagsEntity tagsEntity) {
        tagsEntity.setGmtModified(new Date());
        tagsService.updateById(tagsEntity);
        return R.ok();
    }


    @SysLog("标签列表")
    @RequestMapping("/tag/all-list")
    public R tagList() {
        List<TagsEntity> list = tagsService.selectList(null);
        return R.ok().put("list", list);
    }

    @SysLog("会员标签")
    @RequestMapping("/member/memberTags/{id}")
    public R memberTags(@PathVariable("id") Integer id) {
        if (id == null) {
            return R.error(404, "未找到会员信息");
        }
        Wrapper<MemberTagEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("member_id", id).eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        List<MemberTagEntity> list = memberTagService.selectList(wrapper);
        return R.ok().put("list", list);
    }

    @SysLog("更新或添加会员标签")
    @RequestMapping("/member/add-or-update-tags")
    public R memberUpdateTags(@RequestBody Map<String, String> params) {
        String id = params.get("memberId");
        String tagId = params.get("tagId");
        String tagName = params.get("tagName");
        if (StringUtils.isBlank(id)) {
            return R.error(404, "未找到会员");
        }

        if (StringUtils.isBlank(tagId)) {
            return R.error(401, "未选择标签");
        }
        MemberTagEntity tagEntity = new MemberTagEntity();
        tagEntity.setMemberId(Integer.valueOf(id));
        tagEntity.setTagId(Integer.valueOf(tagId));
        tagEntity.setTagName(tagName);
        memberTagService.insertOrUpdate(tagEntity);
        return R.ok();
    }

    @SysLog("更新或添加会员标签")
    @RequestMapping("/member/delete-tags")
    public R memberDeleteTags(@RequestBody Map<String, String> params) {
        String id = params.get("memberId");
        String tagId = params.get("tagId");
        if (StringUtils.isBlank(id)) {
            return R.error(404, "未找到会员");
        }

        if (StringUtils.isBlank(tagId)) {
            return R.error(401, "未选择标签");
        }

        Wrapper<MemberTagEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("member_id", Integer.valueOf(id)).eq("tag_id", tagId);
        memberTagService.delete(wrapper);
        return R.ok();
    }


    @SysLog("文章添加并发布")
    @RequestMapping("/message/saveAndPublish")
    public R messageSaveAndPublish(@RequestBody ContentHtmlEntity contentHtmlEntity) {
        contentHtmlService.insertAndPublish(contentHtmlEntity);
        return R.ok();
    }


    @SysLog("文章添加并发布")
    @RequestMapping("/message/updateAndPublish")
    public R messageUpdateAndPublish(@RequestBody ContentHtmlEntity contentHtmlEntity) {
        contentHtmlService.updateAndPublish(contentHtmlEntity);
        return R.ok();
    }


    @SysLog("文章发布")
    @RequestMapping("/message/publish")
    public R messagePublish(@RequestBody ContentHtmlEntity contentHtmlEntity) {
        contentHtmlService.publish(contentHtmlEntity);
        return R.ok();
    }


}
