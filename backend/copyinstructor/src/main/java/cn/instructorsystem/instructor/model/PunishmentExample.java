package cn.instructorsystem.instructor.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PunishmentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PunishmentExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andStuNameIsNull() {
            addCriterion("stu_name is null");
            return (Criteria) this;
        }

        public Criteria andStuNameIsNotNull() {
            addCriterion("stu_name is not null");
            return (Criteria) this;
        }

        public Criteria andStuNameEqualTo(String value) {
            addCriterion("stu_name =", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameNotEqualTo(String value) {
            addCriterion("stu_name <>", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameGreaterThan(String value) {
            addCriterion("stu_name >", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameGreaterThanOrEqualTo(String value) {
            addCriterion("stu_name >=", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameLessThan(String value) {
            addCriterion("stu_name <", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameLessThanOrEqualTo(String value) {
            addCriterion("stu_name <=", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameLike(String value) {
            addCriterion("stu_name like", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameNotLike(String value) {
            addCriterion("stu_name not like", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameIn(List<String> values) {
            addCriterion("stu_name in", values, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameNotIn(List<String> values) {
            addCriterion("stu_name not in", values, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameBetween(String value1, String value2) {
            addCriterion("stu_name between", value1, value2, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameNotBetween(String value1, String value2) {
            addCriterion("stu_name not between", value1, value2, "stuName");
            return (Criteria) this;
        }

        public Criteria andPunishmentReasonIsNull() {
            addCriterion("punishment_reason is null");
            return (Criteria) this;
        }

        public Criteria andPunishmentReasonIsNotNull() {
            addCriterion("punishment_reason is not null");
            return (Criteria) this;
        }

        public Criteria andPunishmentReasonEqualTo(String value) {
            addCriterion("punishment_reason =", value, "punishmentReason");
            return (Criteria) this;
        }

        public Criteria andPunishmentReasonNotEqualTo(String value) {
            addCriterion("punishment_reason <>", value, "punishmentReason");
            return (Criteria) this;
        }

        public Criteria andPunishmentReasonGreaterThan(String value) {
            addCriterion("punishment_reason >", value, "punishmentReason");
            return (Criteria) this;
        }

        public Criteria andPunishmentReasonGreaterThanOrEqualTo(String value) {
            addCriterion("punishment_reason >=", value, "punishmentReason");
            return (Criteria) this;
        }

        public Criteria andPunishmentReasonLessThan(String value) {
            addCriterion("punishment_reason <", value, "punishmentReason");
            return (Criteria) this;
        }

        public Criteria andPunishmentReasonLessThanOrEqualTo(String value) {
            addCriterion("punishment_reason <=", value, "punishmentReason");
            return (Criteria) this;
        }

        public Criteria andPunishmentReasonLike(String value) {
            addCriterion("punishment_reason like", value, "punishmentReason");
            return (Criteria) this;
        }

        public Criteria andPunishmentReasonNotLike(String value) {
            addCriterion("punishment_reason not like", value, "punishmentReason");
            return (Criteria) this;
        }

        public Criteria andPunishmentReasonIn(List<String> values) {
            addCriterion("punishment_reason in", values, "punishmentReason");
            return (Criteria) this;
        }

        public Criteria andPunishmentReasonNotIn(List<String> values) {
            addCriterion("punishment_reason not in", values, "punishmentReason");
            return (Criteria) this;
        }

        public Criteria andPunishmentReasonBetween(String value1, String value2) {
            addCriterion("punishment_reason between", value1, value2, "punishmentReason");
            return (Criteria) this;
        }

        public Criteria andPunishmentReasonNotBetween(String value1, String value2) {
            addCriterion("punishment_reason not between", value1, value2, "punishmentReason");
            return (Criteria) this;
        }

        public Criteria andPunishmentTimeIsNull() {
            addCriterion("punishment_time is null");
            return (Criteria) this;
        }

        public Criteria andPunishmentTimeIsNotNull() {
            addCriterion("punishment_time is not null");
            return (Criteria) this;
        }

        public Criteria andPunishmentTimeEqualTo(Date value) {
            addCriterionForJDBCDate("punishment_time =", value, "punishmentTime");
            return (Criteria) this;
        }

        public Criteria andPunishmentTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("punishment_time <>", value, "punishmentTime");
            return (Criteria) this;
        }

        public Criteria andPunishmentTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("punishment_time >", value, "punishmentTime");
            return (Criteria) this;
        }

        public Criteria andPunishmentTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("punishment_time >=", value, "punishmentTime");
            return (Criteria) this;
        }

        public Criteria andPunishmentTimeLessThan(Date value) {
            addCriterionForJDBCDate("punishment_time <", value, "punishmentTime");
            return (Criteria) this;
        }

        public Criteria andPunishmentTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("punishment_time <=", value, "punishmentTime");
            return (Criteria) this;
        }

        public Criteria andPunishmentTimeIn(List<Date> values) {
            addCriterionForJDBCDate("punishment_time in", values, "punishmentTime");
            return (Criteria) this;
        }

        public Criteria andPunishmentTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("punishment_time not in", values, "punishmentTime");
            return (Criteria) this;
        }

        public Criteria andPunishmentTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("punishment_time between", value1, value2, "punishmentTime");
            return (Criteria) this;
        }

        public Criteria andPunishmentTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("punishment_time not between", value1, value2, "punishmentTime");
            return (Criteria) this;
        }

        public Criteria andInsAccountIsNull() {
            addCriterion("ins_account is null");
            return (Criteria) this;
        }

        public Criteria andInsAccountIsNotNull() {
            addCriterion("ins_account is not null");
            return (Criteria) this;
        }

        public Criteria andInsAccountEqualTo(String value) {
            addCriterion("ins_account =", value, "insAccount");
            return (Criteria) this;
        }

        public Criteria andInsAccountNotEqualTo(String value) {
            addCriterion("ins_account <>", value, "insAccount");
            return (Criteria) this;
        }

        public Criteria andInsAccountGreaterThan(String value) {
            addCriterion("ins_account >", value, "insAccount");
            return (Criteria) this;
        }

        public Criteria andInsAccountGreaterThanOrEqualTo(String value) {
            addCriterion("ins_account >=", value, "insAccount");
            return (Criteria) this;
        }

        public Criteria andInsAccountLessThan(String value) {
            addCriterion("ins_account <", value, "insAccount");
            return (Criteria) this;
        }

        public Criteria andInsAccountLessThanOrEqualTo(String value) {
            addCriterion("ins_account <=", value, "insAccount");
            return (Criteria) this;
        }

        public Criteria andInsAccountLike(String value) {
            addCriterion("ins_account like", value, "insAccount");
            return (Criteria) this;
        }

        public Criteria andInsAccountNotLike(String value) {
            addCriterion("ins_account not like", value, "insAccount");
            return (Criteria) this;
        }

        public Criteria andInsAccountIn(List<String> values) {
            addCriterion("ins_account in", values, "insAccount");
            return (Criteria) this;
        }

        public Criteria andInsAccountNotIn(List<String> values) {
            addCriterion("ins_account not in", values, "insAccount");
            return (Criteria) this;
        }

        public Criteria andInsAccountBetween(String value1, String value2) {
            addCriterion("ins_account between", value1, value2, "insAccount");
            return (Criteria) this;
        }

        public Criteria andInsAccountNotBetween(String value1, String value2) {
            addCriterion("ins_account not between", value1, value2, "insAccount");
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