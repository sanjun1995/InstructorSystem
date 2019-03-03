package cn.instructorsystem.instructor.model;

import java.util.ArrayList;
import java.util.List;

public class AchievementExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AchievementExample() {
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

        public Criteria andScore1IsNull() {
            addCriterion("score1 is null");
            return (Criteria) this;
        }

        public Criteria andScore1IsNotNull() {
            addCriterion("score1 is not null");
            return (Criteria) this;
        }

        public Criteria andScore1EqualTo(String value) {
            addCriterion("score1 =", value, "score1");
            return (Criteria) this;
        }

        public Criteria andScore1NotEqualTo(String value) {
            addCriterion("score1 <>", value, "score1");
            return (Criteria) this;
        }

        public Criteria andScore1GreaterThan(String value) {
            addCriterion("score1 >", value, "score1");
            return (Criteria) this;
        }

        public Criteria andScore1GreaterThanOrEqualTo(String value) {
            addCriterion("score1 >=", value, "score1");
            return (Criteria) this;
        }

        public Criteria andScore1LessThan(String value) {
            addCriterion("score1 <", value, "score1");
            return (Criteria) this;
        }

        public Criteria andScore1LessThanOrEqualTo(String value) {
            addCriterion("score1 <=", value, "score1");
            return (Criteria) this;
        }

        public Criteria andScore1Like(String value) {
            addCriterion("score1 like", value, "score1");
            return (Criteria) this;
        }

        public Criteria andScore1NotLike(String value) {
            addCriterion("score1 not like", value, "score1");
            return (Criteria) this;
        }

        public Criteria andScore1In(List<String> values) {
            addCriterion("score1 in", values, "score1");
            return (Criteria) this;
        }

        public Criteria andScore1NotIn(List<String> values) {
            addCriterion("score1 not in", values, "score1");
            return (Criteria) this;
        }

        public Criteria andScore1Between(String value1, String value2) {
            addCriterion("score1 between", value1, value2, "score1");
            return (Criteria) this;
        }

        public Criteria andScore1NotBetween(String value1, String value2) {
            addCriterion("score1 not between", value1, value2, "score1");
            return (Criteria) this;
        }

        public Criteria andScore2IsNull() {
            addCriterion("score2 is null");
            return (Criteria) this;
        }

        public Criteria andScore2IsNotNull() {
            addCriterion("score2 is not null");
            return (Criteria) this;
        }

        public Criteria andScore2EqualTo(String value) {
            addCriterion("score2 =", value, "score2");
            return (Criteria) this;
        }

        public Criteria andScore2NotEqualTo(String value) {
            addCriterion("score2 <>", value, "score2");
            return (Criteria) this;
        }

        public Criteria andScore2GreaterThan(String value) {
            addCriterion("score2 >", value, "score2");
            return (Criteria) this;
        }

        public Criteria andScore2GreaterThanOrEqualTo(String value) {
            addCriterion("score2 >=", value, "score2");
            return (Criteria) this;
        }

        public Criteria andScore2LessThan(String value) {
            addCriterion("score2 <", value, "score2");
            return (Criteria) this;
        }

        public Criteria andScore2LessThanOrEqualTo(String value) {
            addCriterion("score2 <=", value, "score2");
            return (Criteria) this;
        }

        public Criteria andScore2Like(String value) {
            addCriterion("score2 like", value, "score2");
            return (Criteria) this;
        }

        public Criteria andScore2NotLike(String value) {
            addCriterion("score2 not like", value, "score2");
            return (Criteria) this;
        }

        public Criteria andScore2In(List<String> values) {
            addCriterion("score2 in", values, "score2");
            return (Criteria) this;
        }

        public Criteria andScore2NotIn(List<String> values) {
            addCriterion("score2 not in", values, "score2");
            return (Criteria) this;
        }

        public Criteria andScore2Between(String value1, String value2) {
            addCriterion("score2 between", value1, value2, "score2");
            return (Criteria) this;
        }

        public Criteria andScore2NotBetween(String value1, String value2) {
            addCriterion("score2 not between", value1, value2, "score2");
            return (Criteria) this;
        }

        public Criteria andScore3IsNull() {
            addCriterion("score3 is null");
            return (Criteria) this;
        }

        public Criteria andScore3IsNotNull() {
            addCriterion("score3 is not null");
            return (Criteria) this;
        }

        public Criteria andScore3EqualTo(String value) {
            addCriterion("score3 =", value, "score3");
            return (Criteria) this;
        }

        public Criteria andScore3NotEqualTo(String value) {
            addCriterion("score3 <>", value, "score3");
            return (Criteria) this;
        }

        public Criteria andScore3GreaterThan(String value) {
            addCriterion("score3 >", value, "score3");
            return (Criteria) this;
        }

        public Criteria andScore3GreaterThanOrEqualTo(String value) {
            addCriterion("score3 >=", value, "score3");
            return (Criteria) this;
        }

        public Criteria andScore3LessThan(String value) {
            addCriterion("score3 <", value, "score3");
            return (Criteria) this;
        }

        public Criteria andScore3LessThanOrEqualTo(String value) {
            addCriterion("score3 <=", value, "score3");
            return (Criteria) this;
        }

        public Criteria andScore3Like(String value) {
            addCriterion("score3 like", value, "score3");
            return (Criteria) this;
        }

        public Criteria andScore3NotLike(String value) {
            addCriterion("score3 not like", value, "score3");
            return (Criteria) this;
        }

        public Criteria andScore3In(List<String> values) {
            addCriterion("score3 in", values, "score3");
            return (Criteria) this;
        }

        public Criteria andScore3NotIn(List<String> values) {
            addCriterion("score3 not in", values, "score3");
            return (Criteria) this;
        }

        public Criteria andScore3Between(String value1, String value2) {
            addCriterion("score3 between", value1, value2, "score3");
            return (Criteria) this;
        }

        public Criteria andScore3NotBetween(String value1, String value2) {
            addCriterion("score3 not between", value1, value2, "score3");
            return (Criteria) this;
        }

        public Criteria andScore4IsNull() {
            addCriterion("score4 is null");
            return (Criteria) this;
        }

        public Criteria andScore4IsNotNull() {
            addCriterion("score4 is not null");
            return (Criteria) this;
        }

        public Criteria andScore4EqualTo(String value) {
            addCriterion("score4 =", value, "score4");
            return (Criteria) this;
        }

        public Criteria andScore4NotEqualTo(String value) {
            addCriterion("score4 <>", value, "score4");
            return (Criteria) this;
        }

        public Criteria andScore4GreaterThan(String value) {
            addCriterion("score4 >", value, "score4");
            return (Criteria) this;
        }

        public Criteria andScore4GreaterThanOrEqualTo(String value) {
            addCriterion("score4 >=", value, "score4");
            return (Criteria) this;
        }

        public Criteria andScore4LessThan(String value) {
            addCriterion("score4 <", value, "score4");
            return (Criteria) this;
        }

        public Criteria andScore4LessThanOrEqualTo(String value) {
            addCriterion("score4 <=", value, "score4");
            return (Criteria) this;
        }

        public Criteria andScore4Like(String value) {
            addCriterion("score4 like", value, "score4");
            return (Criteria) this;
        }

        public Criteria andScore4NotLike(String value) {
            addCriterion("score4 not like", value, "score4");
            return (Criteria) this;
        }

        public Criteria andScore4In(List<String> values) {
            addCriterion("score4 in", values, "score4");
            return (Criteria) this;
        }

        public Criteria andScore4NotIn(List<String> values) {
            addCriterion("score4 not in", values, "score4");
            return (Criteria) this;
        }

        public Criteria andScore4Between(String value1, String value2) {
            addCriterion("score4 between", value1, value2, "score4");
            return (Criteria) this;
        }

        public Criteria andScore4NotBetween(String value1, String value2) {
            addCriterion("score4 not between", value1, value2, "score4");
            return (Criteria) this;
        }

        public Criteria andComprehensive1IsNull() {
            addCriterion("comprehensive1 is null");
            return (Criteria) this;
        }

        public Criteria andComprehensive1IsNotNull() {
            addCriterion("comprehensive1 is not null");
            return (Criteria) this;
        }

        public Criteria andComprehensive1EqualTo(String value) {
            addCriterion("comprehensive1 =", value, "comprehensive1");
            return (Criteria) this;
        }

        public Criteria andComprehensive1NotEqualTo(String value) {
            addCriterion("comprehensive1 <>", value, "comprehensive1");
            return (Criteria) this;
        }

        public Criteria andComprehensive1GreaterThan(String value) {
            addCriterion("comprehensive1 >", value, "comprehensive1");
            return (Criteria) this;
        }

        public Criteria andComprehensive1GreaterThanOrEqualTo(String value) {
            addCriterion("comprehensive1 >=", value, "comprehensive1");
            return (Criteria) this;
        }

        public Criteria andComprehensive1LessThan(String value) {
            addCriterion("comprehensive1 <", value, "comprehensive1");
            return (Criteria) this;
        }

        public Criteria andComprehensive1LessThanOrEqualTo(String value) {
            addCriterion("comprehensive1 <=", value, "comprehensive1");
            return (Criteria) this;
        }

        public Criteria andComprehensive1Like(String value) {
            addCriterion("comprehensive1 like", value, "comprehensive1");
            return (Criteria) this;
        }

        public Criteria andComprehensive1NotLike(String value) {
            addCriterion("comprehensive1 not like", value, "comprehensive1");
            return (Criteria) this;
        }

        public Criteria andComprehensive1In(List<String> values) {
            addCriterion("comprehensive1 in", values, "comprehensive1");
            return (Criteria) this;
        }

        public Criteria andComprehensive1NotIn(List<String> values) {
            addCriterion("comprehensive1 not in", values, "comprehensive1");
            return (Criteria) this;
        }

        public Criteria andComprehensive1Between(String value1, String value2) {
            addCriterion("comprehensive1 between", value1, value2, "comprehensive1");
            return (Criteria) this;
        }

        public Criteria andComprehensive1NotBetween(String value1, String value2) {
            addCriterion("comprehensive1 not between", value1, value2, "comprehensive1");
            return (Criteria) this;
        }

        public Criteria andComprehensive2IsNull() {
            addCriterion("comprehensive2 is null");
            return (Criteria) this;
        }

        public Criteria andComprehensive2IsNotNull() {
            addCriterion("comprehensive2 is not null");
            return (Criteria) this;
        }

        public Criteria andComprehensive2EqualTo(String value) {
            addCriterion("comprehensive2 =", value, "comprehensive2");
            return (Criteria) this;
        }

        public Criteria andComprehensive2NotEqualTo(String value) {
            addCriterion("comprehensive2 <>", value, "comprehensive2");
            return (Criteria) this;
        }

        public Criteria andComprehensive2GreaterThan(String value) {
            addCriterion("comprehensive2 >", value, "comprehensive2");
            return (Criteria) this;
        }

        public Criteria andComprehensive2GreaterThanOrEqualTo(String value) {
            addCriterion("comprehensive2 >=", value, "comprehensive2");
            return (Criteria) this;
        }

        public Criteria andComprehensive2LessThan(String value) {
            addCriterion("comprehensive2 <", value, "comprehensive2");
            return (Criteria) this;
        }

        public Criteria andComprehensive2LessThanOrEqualTo(String value) {
            addCriterion("comprehensive2 <=", value, "comprehensive2");
            return (Criteria) this;
        }

        public Criteria andComprehensive2Like(String value) {
            addCriterion("comprehensive2 like", value, "comprehensive2");
            return (Criteria) this;
        }

        public Criteria andComprehensive2NotLike(String value) {
            addCriterion("comprehensive2 not like", value, "comprehensive2");
            return (Criteria) this;
        }

        public Criteria andComprehensive2In(List<String> values) {
            addCriterion("comprehensive2 in", values, "comprehensive2");
            return (Criteria) this;
        }

        public Criteria andComprehensive2NotIn(List<String> values) {
            addCriterion("comprehensive2 not in", values, "comprehensive2");
            return (Criteria) this;
        }

        public Criteria andComprehensive2Between(String value1, String value2) {
            addCriterion("comprehensive2 between", value1, value2, "comprehensive2");
            return (Criteria) this;
        }

        public Criteria andComprehensive2NotBetween(String value1, String value2) {
            addCriterion("comprehensive2 not between", value1, value2, "comprehensive2");
            return (Criteria) this;
        }

        public Criteria andComprehensive3IsNull() {
            addCriterion("comprehensive3 is null");
            return (Criteria) this;
        }

        public Criteria andComprehensive3IsNotNull() {
            addCriterion("comprehensive3 is not null");
            return (Criteria) this;
        }

        public Criteria andComprehensive3EqualTo(String value) {
            addCriterion("comprehensive3 =", value, "comprehensive3");
            return (Criteria) this;
        }

        public Criteria andComprehensive3NotEqualTo(String value) {
            addCriterion("comprehensive3 <>", value, "comprehensive3");
            return (Criteria) this;
        }

        public Criteria andComprehensive3GreaterThan(String value) {
            addCriterion("comprehensive3 >", value, "comprehensive3");
            return (Criteria) this;
        }

        public Criteria andComprehensive3GreaterThanOrEqualTo(String value) {
            addCriterion("comprehensive3 >=", value, "comprehensive3");
            return (Criteria) this;
        }

        public Criteria andComprehensive3LessThan(String value) {
            addCriterion("comprehensive3 <", value, "comprehensive3");
            return (Criteria) this;
        }

        public Criteria andComprehensive3LessThanOrEqualTo(String value) {
            addCriterion("comprehensive3 <=", value, "comprehensive3");
            return (Criteria) this;
        }

        public Criteria andComprehensive3Like(String value) {
            addCriterion("comprehensive3 like", value, "comprehensive3");
            return (Criteria) this;
        }

        public Criteria andComprehensive3NotLike(String value) {
            addCriterion("comprehensive3 not like", value, "comprehensive3");
            return (Criteria) this;
        }

        public Criteria andComprehensive3In(List<String> values) {
            addCriterion("comprehensive3 in", values, "comprehensive3");
            return (Criteria) this;
        }

        public Criteria andComprehensive3NotIn(List<String> values) {
            addCriterion("comprehensive3 not in", values, "comprehensive3");
            return (Criteria) this;
        }

        public Criteria andComprehensive3Between(String value1, String value2) {
            addCriterion("comprehensive3 between", value1, value2, "comprehensive3");
            return (Criteria) this;
        }

        public Criteria andComprehensive3NotBetween(String value1, String value2) {
            addCriterion("comprehensive3 not between", value1, value2, "comprehensive3");
            return (Criteria) this;
        }

        public Criteria andComprehensive4IsNull() {
            addCriterion("comprehensive4 is null");
            return (Criteria) this;
        }

        public Criteria andComprehensive4IsNotNull() {
            addCriterion("comprehensive4 is not null");
            return (Criteria) this;
        }

        public Criteria andComprehensive4EqualTo(String value) {
            addCriterion("comprehensive4 =", value, "comprehensive4");
            return (Criteria) this;
        }

        public Criteria andComprehensive4NotEqualTo(String value) {
            addCriterion("comprehensive4 <>", value, "comprehensive4");
            return (Criteria) this;
        }

        public Criteria andComprehensive4GreaterThan(String value) {
            addCriterion("comprehensive4 >", value, "comprehensive4");
            return (Criteria) this;
        }

        public Criteria andComprehensive4GreaterThanOrEqualTo(String value) {
            addCriterion("comprehensive4 >=", value, "comprehensive4");
            return (Criteria) this;
        }

        public Criteria andComprehensive4LessThan(String value) {
            addCriterion("comprehensive4 <", value, "comprehensive4");
            return (Criteria) this;
        }

        public Criteria andComprehensive4LessThanOrEqualTo(String value) {
            addCriterion("comprehensive4 <=", value, "comprehensive4");
            return (Criteria) this;
        }

        public Criteria andComprehensive4Like(String value) {
            addCriterion("comprehensive4 like", value, "comprehensive4");
            return (Criteria) this;
        }

        public Criteria andComprehensive4NotLike(String value) {
            addCriterion("comprehensive4 not like", value, "comprehensive4");
            return (Criteria) this;
        }

        public Criteria andComprehensive4In(List<String> values) {
            addCriterion("comprehensive4 in", values, "comprehensive4");
            return (Criteria) this;
        }

        public Criteria andComprehensive4NotIn(List<String> values) {
            addCriterion("comprehensive4 not in", values, "comprehensive4");
            return (Criteria) this;
        }

        public Criteria andComprehensive4Between(String value1, String value2) {
            addCriterion("comprehensive4 between", value1, value2, "comprehensive4");
            return (Criteria) this;
        }

        public Criteria andComprehensive4NotBetween(String value1, String value2) {
            addCriterion("comprehensive4 not between", value1, value2, "comprehensive4");
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