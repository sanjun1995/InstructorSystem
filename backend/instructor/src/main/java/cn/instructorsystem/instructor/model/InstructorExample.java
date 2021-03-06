package cn.instructorsystem.instructor.model;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

public class InstructorExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InstructorExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAccountIsNull() {
            addCriterion("account is null");
            return (Criteria) this;
        }

        public Criteria andAccountIsNotNull() {
            addCriterion("account is not null");
            return (Criteria) this;
        }

        public Criteria andAccountEqualTo(String value) {
            addCriterion("account =", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotEqualTo(String value) {
            addCriterion("account <>", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThan(String value) {
            addCriterion("account >", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThanOrEqualTo(String value) {
            addCriterion("account >=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThan(String value) {
            addCriterion("account <", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThanOrEqualTo(String value) {
            addCriterion("account <=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLike(String value) {
            addCriterion("account like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotLike(String value) {
            addCriterion("account not like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountIn(List<String> values) {
            addCriterion("account in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotIn(List<String> values) {
            addCriterion("account not in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountBetween(String value1, String value2) {
            addCriterion("account between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotBetween(String value1, String value2) {
            addCriterion("account not between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andInsNameIsNull() {
            addCriterion("ins_name is null");
            return (Criteria) this;
        }

        public Criteria andInsNameIsNotNull() {
            addCriterion("ins_name is not null");
            return (Criteria) this;
        }

        public Criteria andInsNameEqualTo(String value) {
            addCriterion("ins_name =", value, "insName");
            return (Criteria) this;
        }

        public Criteria andInsNameNotEqualTo(String value) {
            addCriterion("ins_name <>", value, "insName");
            return (Criteria) this;
        }

        public Criteria andInsNameGreaterThan(String value) {
            addCriterion("ins_name >", value, "insName");
            return (Criteria) this;
        }

        public Criteria andInsNameGreaterThanOrEqualTo(String value) {
            addCriterion("ins_name >=", value, "insName");
            return (Criteria) this;
        }

        public Criteria andInsNameLessThan(String value) {
            addCriterion("ins_name <", value, "insName");
            return (Criteria) this;
        }

        public Criteria andInsNameLessThanOrEqualTo(String value) {
            addCriterion("ins_name <=", value, "insName");
            return (Criteria) this;
        }

        public Criteria andInsNameLike(String value) {
            addCriterion("ins_name like", value, "insName");
            return (Criteria) this;
        }

        public Criteria andInsNameNotLike(String value) {
            addCriterion("ins_name not like", value, "insName");
            return (Criteria) this;
        }

        public Criteria andInsNameIn(List<String> values) {
            addCriterion("ins_name in", values, "insName");
            return (Criteria) this;
        }

        public Criteria andInsNameNotIn(List<String> values) {
            addCriterion("ins_name not in", values, "insName");
            return (Criteria) this;
        }

        public Criteria andInsNameBetween(String value1, String value2) {
            addCriterion("ins_name between", value1, value2, "insName");
            return (Criteria) this;
        }

        public Criteria andInsNameNotBetween(String value1, String value2) {
            addCriterion("ins_name not between", value1, value2, "insName");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(Integer value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(Integer value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(Integer value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(Integer value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(Integer value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(Integer value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<Integer> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<Integer> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(Integer value1, Integer value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(Integer value1, Integer value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andPhoneNumIsNull() {
            addCriterion("phone_num is null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumIsNotNull() {
            addCriterion("phone_num is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumEqualTo(String value) {
            addCriterion("phone_num =", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotEqualTo(String value) {
            addCriterion("phone_num <>", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumGreaterThan(String value) {
            addCriterion("phone_num >", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumGreaterThanOrEqualTo(String value) {
            addCriterion("phone_num >=", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumLessThan(String value) {
            addCriterion("phone_num <", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumLessThanOrEqualTo(String value) {
            addCriterion("phone_num <=", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumLike(String value) {
            addCriterion("phone_num like", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotLike(String value) {
            addCriterion("phone_num not like", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumIn(List<String> values) {
            addCriterion("phone_num in", values, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotIn(List<String> values) {
            addCriterion("phone_num not in", values, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumBetween(String value1, String value2) {
            addCriterion("phone_num between", value1, value2, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotBetween(String value1, String value2) {
            addCriterion("phone_num not between", value1, value2, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andOfficeLocationIsNull() {
            addCriterion("office_location is null");
            return (Criteria) this;
        }

        public Criteria andOfficeLocationIsNotNull() {
            addCriterion("office_location is not null");
            return (Criteria) this;
        }

        public Criteria andOfficeLocationEqualTo(String value) {
            addCriterion("office_location =", value, "officeLocation");
            return (Criteria) this;
        }

        public Criteria andOfficeLocationNotEqualTo(String value) {
            addCriterion("office_location <>", value, "officeLocation");
            return (Criteria) this;
        }

        public Criteria andOfficeLocationGreaterThan(String value) {
            addCriterion("office_location >", value, "officeLocation");
            return (Criteria) this;
        }

        public Criteria andOfficeLocationGreaterThanOrEqualTo(String value) {
            addCriterion("office_location >=", value, "officeLocation");
            return (Criteria) this;
        }

        public Criteria andOfficeLocationLessThan(String value) {
            addCriterion("office_location <", value, "officeLocation");
            return (Criteria) this;
        }

        public Criteria andOfficeLocationLessThanOrEqualTo(String value) {
            addCriterion("office_location <=", value, "officeLocation");
            return (Criteria) this;
        }

        public Criteria andOfficeLocationLike(String value) {
            addCriterion("office_location like", value, "officeLocation");
            return (Criteria) this;
        }

        public Criteria andOfficeLocationNotLike(String value) {
            addCriterion("office_location not like", value, "officeLocation");
            return (Criteria) this;
        }

        public Criteria andOfficeLocationIn(List<String> values) {
            addCriterion("office_location in", values, "officeLocation");
            return (Criteria) this;
        }

        public Criteria andOfficeLocationNotIn(List<String> values) {
            addCriterion("office_location not in", values, "officeLocation");
            return (Criteria) this;
        }

        public Criteria andOfficeLocationBetween(String value1, String value2) {
            addCriterion("office_location between", value1, value2, "officeLocation");
            return (Criteria) this;
        }

        public Criteria andOfficeLocationNotBetween(String value1, String value2) {
            addCriterion("office_location not between", value1, value2, "officeLocation");
            return (Criteria) this;
        }

        public Criteria andHeadPathIsNull() {
            addCriterion("head_path is null");
            return (Criteria) this;
        }

        public Criteria andHeadPathIsNotNull() {
            addCriterion("head_path is not null");
            return (Criteria) this;
        }

        public Criteria andHeadPathEqualTo(String value) {
            addCriterion("head_path =", value, "headPath");
            return (Criteria) this;
        }

        public Criteria andHeadPathNotEqualTo(String value) {
            addCriterion("head_path <>", value, "headPath");
            return (Criteria) this;
        }

        public Criteria andHeadPathGreaterThan(String value) {
            addCriterion("head_path >", value, "headPath");
            return (Criteria) this;
        }

        public Criteria andHeadPathGreaterThanOrEqualTo(String value) {
            addCriterion("head_path >=", value, "headPath");
            return (Criteria) this;
        }

        public Criteria andHeadPathLessThan(String value) {
            addCriterion("head_path <", value, "headPath");
            return (Criteria) this;
        }

        public Criteria andHeadPathLessThanOrEqualTo(String value) {
            addCriterion("head_path <=", value, "headPath");
            return (Criteria) this;
        }

        public Criteria andHeadPathLike(String value) {
            addCriterion("head_path like", value, "headPath");
            return (Criteria) this;
        }

        public Criteria andHeadPathNotLike(String value) {
            addCriterion("head_path not like", value, "headPath");
            return (Criteria) this;
        }

        public Criteria andHeadPathIn(List<String> values) {
            addCriterion("head_path in", values, "headPath");
            return (Criteria) this;
        }

        public Criteria andHeadPathNotIn(List<String> values) {
            addCriterion("head_path not in", values, "headPath");
            return (Criteria) this;
        }

        public Criteria andHeadPathBetween(String value1, String value2) {
            addCriterion("head_path between", value1, value2, "headPath");
            return (Criteria) this;
        }

        public Criteria andHeadPathNotBetween(String value1, String value2) {
            addCriterion("head_path not between", value1, value2, "headPath");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}