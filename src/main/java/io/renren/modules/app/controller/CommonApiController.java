package io.renren.modules.app.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import io.renren.common.annotation.UserLog;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.app.form.MemberForm;
import io.renren.modules.app.utils.JwtUtils;
import io.renren.modules.doctor.entity.CommunityEntity;
import io.renren.modules.doctor.entity.EnterpriseEntity;
import io.renren.modules.doctor.service.CommunityService;
import io.renren.modules.doctor.service.EnterpriseService;
import io.renren.modules.member.entity.*;
import io.renren.modules.member.service.*;
import io.renren.modules.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app")
@Api("APP通用接口")
public class CommonApiController {

    @Autowired
    private CommunityService communityService;
    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private MemberLevelService levelService;
    @Autowired
    private MemberLifeService lifeService;
    @Autowired
    private MemberDrugService drugService;
    @Autowired
    private MemberCheckService checkService;
    @Autowired
    private MemberAllergyService allergyService;
    @Autowired
    private MemberGenenticDiseaseService genenticDiseaseService;
    @Autowired
    private MemberCurrentService currentService;
    @Autowired
    private MemberHistoryDiseaseService diseaseService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberChildService memberChildService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/communities")
    @UserLog("获取所有的社区")
    public R communities() {
        Wrapper<CommunityEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        return R.ok().put("communities", communityService.selectList(wrapper));
    }

    @GetMapping("/enterprises")
    @UserLog("获取所有的企业")
    public R enterprises() {
        Wrapper<EnterpriseEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        return R.ok().put("enterprises", enterpriseService.selectList(wrapper));
    }

    @GetMapping("/levels")
    @UserLog("获取所有会员等级")
    public R levels(){
        Wrapper<MemberLevelEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("is_deleted", Constant.DeleteType.un_deleted.getValue()).orderBy("sort");
        List<MemberLevelEntity > list = levelService.selectList(wrapper);
        return R.ok().put("list",list);
    }



    /**
     * 检查列表
     */
    @RequestMapping("/drug-list")
    public R drugList(@RequestParam Map<String, Object> params) {
        PageUtils page = drugService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 修改
     */
    @RequestMapping("/update-check")
    public R updateCheck(@RequestBody MemberCheckEntity checkEntity) {
        checkService.updateById(checkEntity);
        return R.ok();
    }


    /**
     * 信息
     */
    @RequestMapping("/info-check/{id}")
    public R checkInfo(@PathVariable("id") Integer id) {
        MemberCheckEntity checkEntity = checkService.selectById(id);
        return R.ok().put("check", checkEntity);
    }

    /**
     * 保存
     */
    @RequestMapping("/add-check")
    public R saveCheck(@RequestBody MemberCheckEntity checkEntity) {
        checkEntity.setIsDeleted(Constant.DeleteType.un_deleted.getValue());
        checkService.insert(checkEntity);
        return R.ok();
    }




    @RequestMapping("/update-allergy-disease")
    public R updateAllergyDisease(@RequestBody MemberAllergyEntity allergyEntity) {
        if (allergyEntity.getMemberId() == null) {
            return R.error(404, "未找到会员信息");
        }
        allergyService.updateById(allergyEntity);
        return R.ok();
    }




    /**
     * 检查列表
     */
    @RequestMapping("/check-list")
    public R checkList(@RequestParam Map<String, Object> params) {
        PageUtils page = checkService.queryPage(params);
        return R.ok().put("page", page);
    }




    @RequestMapping("/update-genentic-disease")
    public R updateGenenticDisease(@RequestBody MemberGenenticDiseaseEntity genenticDiseaseEntity) {
        if (genenticDiseaseEntity.getMemberId() == null) {
            return R.error(404, "未找到会员信息");
        }
        genenticDiseaseService.updateById(genenticDiseaseEntity);
        return R.ok();
    }


    //历史
    @RequestMapping("/genentic-disease")
    public R genenticSymptom(@RequestParam Map<String, Object> params) {
        PageUtils page = genenticDiseaseService.queryPage(params);
        return R.ok().put("page", page);
    }


    @RequestMapping("/allergy-disease")
    public R allergySymptom(@RequestParam Map<String, Object> params) {
        PageUtils page = allergyService.queryPage(params);
        return R.ok().put("page", page);
    }


    @RequestMapping("/save-allergy-disease")
    public R saveAllergyDisease(@RequestBody MemberAllergyEntity allergyEntity) {
        if (allergyEntity.getMemberId() == null) {
            return R.error(404, "未找到会员信息");
        }
        allergyEntity.setIsDeleted(Constant.DeleteType.un_deleted.getValue());
        allergyService.insert(allergyEntity);
        return R.ok();
    }



    @RequestMapping("/save-genentic-disease")
    public R saveGenenticDisease(@RequestBody MemberGenenticDiseaseEntity genenticDiseaseEntity) {
        if (genenticDiseaseEntity.getMemberId() == null) {
            return R.error(404, "未找到会员信息");
        }
        genenticDiseaseEntity.setIsDeleted(Constant.DeleteType.un_deleted.getValue());
        genenticDiseaseService.insert(genenticDiseaseEntity);
        return R.ok();
    }



    //历史
    @RequestMapping("/history-disease")
    public R historySymptom(@RequestParam Map<String, Object> params) {
        PageUtils page = diseaseService.queryPage(params);
        return R.ok().put("page", page);
    }

    //当前
    @GetMapping("/current-disease/{memberId}")
    public R currentSymptom(@PathVariable Integer memberId) {
        Wrapper<MemberCurrentEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("member_id", Integer.valueOf(memberId)).eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        MemberCurrentEntity entity = currentService.selectOne(wrapper);
        return R.ok().put("disease", entity);
    }


    @RequestMapping("/save-current-disease")
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

    @RequestMapping("/save-history-disease")
    public R saveHistoryDisease(@RequestBody MemberHistoryDiseaseEntity historyDiseaseEntity) {
        if (historyDiseaseEntity.getMemberId() == null) {
            return R.error(404, "未找到会员信息");
        }
        diseaseService.insert(historyDiseaseEntity);
        return R.ok();
    }

    @RequestMapping("/update-history-disease")
    public R updateHistoryDisease(@RequestBody MemberHistoryDiseaseEntity historyDiseaseEntity) {
        if (historyDiseaseEntity.getMemberId() == null) {
            return R.error(404, "未找到会员信息");
        }
        diseaseService.updateById(historyDiseaseEntity);
        return R.ok();
    }




    @RequestMapping("/child-list")
    public R childList(@RequestParam Map<String, Object> params) {
        PageUtils page = memberService.queryChildPage(params);
        return R.ok().put("page", page);
    }


    @GetMapping("/sn/{sn}")
    public R list(@PathVariable("sn") String sn) {
        Wrapper<MemberEntity> wrapper = new EntityWrapper<>();
        wrapper.like("sn", sn).eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        return R.ok().put("list", memberService.selectList(wrapper));
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        MemberEntity memberEntity = memberService.selectById(id);

        return R.ok().put("member", memberEntity);
    }

    /**
     * 信息
     */
    @RequestMapping("/info-child/{id}")
    public R chidInfo(@PathVariable("id") Integer id) {
        MemberChildEntity childEntity = memberChildService.selectById(id);
        return R.ok().put("child", childEntity);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody MemberForm memberForm) {
        memberService.save(memberForm);
        return R.ok();
    }

    /**
     * 保存
     */
    @RequestMapping("/add-child")
    public R saveChild(@RequestBody MemberChildEntity childEntity) {
        memberChildService.insert(childEntity);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody MemberEntity tblMember) {
        memberService.updateById(tblMember);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update-child")
    public R updateChild(@RequestBody MemberChildEntity childEntity) {
        memberChildService.updateById(childEntity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setIsDeleted(Constant.DeleteType.is_deleted.getValue());
        Wrapper<MemberEntity> wrapper = new EntityWrapper<>();
        wrapper.in("id", Arrays.asList(ids));
        memberService.update(memberEntity,wrapper);
        return R.ok();
    }
    /**
     * 删除
     */
    @RequestMapping("/delete-child")
    public R deleteChild(@RequestBody Integer[] ids) {
        for (int i = 0; i < ids.length; i++) {
            Wrapper<MemberChildEntity> wrapper = new EntityWrapper<>();
            wrapper.eq("id", ids[0]);
            MemberChildEntity childEntity = new MemberChildEntity();
            childEntity.setIsDeleted(Constant.DeleteType.is_deleted.getValue());
            memberChildService.update(childEntity, wrapper);
        }
        return R.ok();
    }

    @RequestMapping("/delete-history-disease")
    public R deleteHistoryDisease(@RequestBody Integer id) {
        MemberHistoryDiseaseEntity historyDiseaseEntity = new MemberHistoryDiseaseEntity();
        historyDiseaseEntity.setIsDeleted(Constant.DeleteType.is_deleted.getValue());
        historyDiseaseEntity.setId(id);
        diseaseService.updateById(historyDiseaseEntity);
        return R.ok();
    }

    @RequestMapping("/delete-genentic-disease")
    public R deleteGenenticDisease(@RequestBody Integer id) {
        MemberGenenticDiseaseEntity genenticDisease = new MemberGenenticDiseaseEntity();
        genenticDisease.setIsDeleted(Constant.DeleteType.is_deleted.getValue());
        genenticDisease.setId(id);
        genenticDiseaseService.updateById(genenticDisease);
        return R.ok();
    }


    @RequestMapping("/delete-allergy-disease")
    public R deleteAllergyDisease(@RequestBody Integer id) {
        MemberAllergyEntity allergyEntity = new MemberAllergyEntity();
        allergyEntity.setIsDeleted(Constant.DeleteType.is_deleted.getValue());
        allergyEntity.setId(id);
        allergyService.updateById(allergyEntity);
        return R.ok();
    }


    @RequestMapping("/delete-check")
    public R deleteCheck(@RequestBody Integer[] ids) {
        MemberCheckEntity checkEntity = new MemberCheckEntity();
        checkEntity.setIsDeleted(Constant.DeleteType.is_deleted.getValue());
        Wrapper<MemberCheckEntity> wrapper = new EntityWrapper<>();
        wrapper.in("id",Arrays.asList(ids));
        checkService.update(checkEntity,wrapper);
        return R.ok();
    }


    @RequestMapping("/delete-drug")
    public R deleteDrug(@RequestBody Integer[] ids) {
        MemberDrugEntity drugEntity = new MemberDrugEntity();
        drugEntity.setIsDeleted(Constant.DeleteType.is_deleted.getValue());
        Wrapper<MemberDrugEntity> wrapper = new EntityWrapper<>();
        wrapper.in("id",Arrays.asList(ids));
        drugService.update(drugEntity,wrapper);
        return R.ok();
    }
}
