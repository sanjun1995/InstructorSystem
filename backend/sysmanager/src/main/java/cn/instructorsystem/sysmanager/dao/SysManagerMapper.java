package cn.instructorsystem.sysmanager.dao;

import java.util.List;

import cn.instructorsystem.sysmanager.model.SysManager;
import cn.instructorsystem.sysmanager.model.SysManagerExample;
import org.apache.ibatis.annotations.Param;

public interface SysManagerMapper {
    int deleteByExample(SysManagerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysManager record);

    int insertSelective(SysManager record);

    List<SysManager> selectByExample(SysManagerExample example);

    SysManager selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysManager record, @Param("example") SysManagerExample example);

    int updateByExample(@Param("record") SysManager record, @Param("example") SysManagerExample example);

    int updateByPrimaryKeySelective(SysManager record);

    int updateByPrimaryKey(SysManager record);
}