package io.renren.modules.doctor.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.app.form.HouseKeeperForm;
import io.renren.modules.doctor.entity.HouseKeeperEntity;

import java.util.List;
import java.util.Map;

public interface HouseKeeperService extends IService<HouseKeeperEntity> {
    PageUtils queryPage(Map<String, Object> params);

    void save(HouseKeeperForm form);

    void update(HouseKeeperForm doctorForm);

    HouseKeeperForm info(Integer id);

    void deleteHouseKeeper(List<Integer> ids);
}
