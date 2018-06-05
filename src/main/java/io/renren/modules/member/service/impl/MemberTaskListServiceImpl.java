package io.renren.modules.member.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.member.dao.MemberTaskListDao;
import io.renren.modules.member.entity.MemberTaskList;
import io.renren.modules.member.service.MemberTaskListService;
import org.springframework.stereotype.Service;

@Service("memberTaskListService")
public class MemberTaskListServiceImpl extends ServiceImpl<MemberTaskListDao, MemberTaskList> implements MemberTaskListService {
}
