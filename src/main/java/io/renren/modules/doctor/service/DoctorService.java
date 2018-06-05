package io.renren.modules.doctor.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.app.form.DoctorForm;
import io.renren.modules.doctor.entity.DoctorEntity;
import io.renren.modules.doctor.entity.DoctorLogEntity;

import javax.print.Doc;
import java.util.List;
import java.util.Map;

/**
 * 医生信息
 *
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:23
 */
public interface DoctorService extends IService<DoctorEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void save(DoctorForm doctorForm);

    void update(DoctorForm doctorForm);

    DoctorForm info(Integer id);

    void deleteDoctor(List<Integer> ids);



}

