package cn.yan.test.spring.boot.database.domain;

import java.util.ArrayList;
import java.util.List;

public class CourseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CourseExample() {
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

        public Criteria andCourIdIsNull() {
            addCriterion("cour_id is null");
            return (Criteria) this;
        }

        public Criteria andCourIdIsNotNull() {
            addCriterion("cour_id is not null");
            return (Criteria) this;
        }

        public Criteria andCourIdEqualTo(Integer value) {
            addCriterion("cour_id =", value, "courId");
            return (Criteria) this;
        }

        public Criteria andCourIdNotEqualTo(Integer value) {
            addCriterion("cour_id <>", value, "courId");
            return (Criteria) this;
        }

        public Criteria andCourIdGreaterThan(Integer value) {
            addCriterion("cour_id >", value, "courId");
            return (Criteria) this;
        }

        public Criteria andCourIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cour_id >=", value, "courId");
            return (Criteria) this;
        }

        public Criteria andCourIdLessThan(Integer value) {
            addCriterion("cour_id <", value, "courId");
            return (Criteria) this;
        }

        public Criteria andCourIdLessThanOrEqualTo(Integer value) {
            addCriterion("cour_id <=", value, "courId");
            return (Criteria) this;
        }

        public Criteria andCourIdIn(List<Integer> values) {
            addCriterion("cour_id in", values, "courId");
            return (Criteria) this;
        }

        public Criteria andCourIdNotIn(List<Integer> values) {
            addCriterion("cour_id not in", values, "courId");
            return (Criteria) this;
        }

        public Criteria andCourIdBetween(Integer value1, Integer value2) {
            addCriterion("cour_id between", value1, value2, "courId");
            return (Criteria) this;
        }

        public Criteria andCourIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cour_id not between", value1, value2, "courId");
            return (Criteria) this;
        }

        public Criteria andCourNameIsNull() {
            addCriterion("cour_name is null");
            return (Criteria) this;
        }

        public Criteria andCourNameIsNotNull() {
            addCriterion("cour_name is not null");
            return (Criteria) this;
        }

        public Criteria andCourNameEqualTo(String value) {
            addCriterion("cour_name =", value, "courName");
            return (Criteria) this;
        }

        public Criteria andCourNameNotEqualTo(String value) {
            addCriterion("cour_name <>", value, "courName");
            return (Criteria) this;
        }

        public Criteria andCourNameGreaterThan(String value) {
            addCriterion("cour_name >", value, "courName");
            return (Criteria) this;
        }

        public Criteria andCourNameGreaterThanOrEqualTo(String value) {
            addCriterion("cour_name >=", value, "courName");
            return (Criteria) this;
        }

        public Criteria andCourNameLessThan(String value) {
            addCriterion("cour_name <", value, "courName");
            return (Criteria) this;
        }

        public Criteria andCourNameLessThanOrEqualTo(String value) {
            addCriterion("cour_name <=", value, "courName");
            return (Criteria) this;
        }

        public Criteria andCourNameLike(String value) {
            addCriterion("cour_name like", value, "courName");
            return (Criteria) this;
        }

        public Criteria andCourNameNotLike(String value) {
            addCriterion("cour_name not like", value, "courName");
            return (Criteria) this;
        }

        public Criteria andCourNameIn(List<String> values) {
            addCriterion("cour_name in", values, "courName");
            return (Criteria) this;
        }

        public Criteria andCourNameNotIn(List<String> values) {
            addCriterion("cour_name not in", values, "courName");
            return (Criteria) this;
        }

        public Criteria andCourNameBetween(String value1, String value2) {
            addCriterion("cour_name between", value1, value2, "courName");
            return (Criteria) this;
        }

        public Criteria andCourNameNotBetween(String value1, String value2) {
            addCriterion("cour_name not between", value1, value2, "courName");
            return (Criteria) this;
        }

        public Criteria andTeaIdIsNull() {
            addCriterion("tea_id is null");
            return (Criteria) this;
        }

        public Criteria andTeaIdIsNotNull() {
            addCriterion("tea_id is not null");
            return (Criteria) this;
        }

        public Criteria andTeaIdEqualTo(String value) {
            addCriterion("tea_id =", value, "teaId");
            return (Criteria) this;
        }

        public Criteria andTeaIdNotEqualTo(String value) {
            addCriterion("tea_id <>", value, "teaId");
            return (Criteria) this;
        }

        public Criteria andTeaIdGreaterThan(String value) {
            addCriterion("tea_id >", value, "teaId");
            return (Criteria) this;
        }

        public Criteria andTeaIdGreaterThanOrEqualTo(String value) {
            addCriterion("tea_id >=", value, "teaId");
            return (Criteria) this;
        }

        public Criteria andTeaIdLessThan(String value) {
            addCriterion("tea_id <", value, "teaId");
            return (Criteria) this;
        }

        public Criteria andTeaIdLessThanOrEqualTo(String value) {
            addCriterion("tea_id <=", value, "teaId");
            return (Criteria) this;
        }

        public Criteria andTeaIdLike(String value) {
            addCriterion("tea_id like", value, "teaId");
            return (Criteria) this;
        }

        public Criteria andTeaIdNotLike(String value) {
            addCriterion("tea_id not like", value, "teaId");
            return (Criteria) this;
        }

        public Criteria andTeaIdIn(List<String> values) {
            addCriterion("tea_id in", values, "teaId");
            return (Criteria) this;
        }

        public Criteria andTeaIdNotIn(List<String> values) {
            addCriterion("tea_id not in", values, "teaId");
            return (Criteria) this;
        }

        public Criteria andTeaIdBetween(String value1, String value2) {
            addCriterion("tea_id between", value1, value2, "teaId");
            return (Criteria) this;
        }

        public Criteria andTeaIdNotBetween(String value1, String value2) {
            addCriterion("tea_id not between", value1, value2, "teaId");
            return (Criteria) this;
        }

        public Criteria andTestNumIsNull() {
            addCriterion("test_num is null");
            return (Criteria) this;
        }

        public Criteria andTestNumIsNotNull() {
            addCriterion("test_num is not null");
            return (Criteria) this;
        }

        public Criteria andTestNumEqualTo(Integer value) {
            addCriterion("test_num =", value, "testNum");
            return (Criteria) this;
        }

        public Criteria andTestNumNotEqualTo(Integer value) {
            addCriterion("test_num <>", value, "testNum");
            return (Criteria) this;
        }

        public Criteria andTestNumGreaterThan(Integer value) {
            addCriterion("test_num >", value, "testNum");
            return (Criteria) this;
        }

        public Criteria andTestNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("test_num >=", value, "testNum");
            return (Criteria) this;
        }

        public Criteria andTestNumLessThan(Integer value) {
            addCriterion("test_num <", value, "testNum");
            return (Criteria) this;
        }

        public Criteria andTestNumLessThanOrEqualTo(Integer value) {
            addCriterion("test_num <=", value, "testNum");
            return (Criteria) this;
        }

        public Criteria andTestNumIn(List<Integer> values) {
            addCriterion("test_num in", values, "testNum");
            return (Criteria) this;
        }

        public Criteria andTestNumNotIn(List<Integer> values) {
            addCriterion("test_num not in", values, "testNum");
            return (Criteria) this;
        }

        public Criteria andTestNumBetween(Integer value1, Integer value2) {
            addCriterion("test_num between", value1, value2, "testNum");
            return (Criteria) this;
        }

        public Criteria andTestNumNotBetween(Integer value1, Integer value2) {
            addCriterion("test_num not between", value1, value2, "testNum");
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