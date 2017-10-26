package com.nf.empst.action;

import com.nf.empst.dao.DeptDAO;
import com.nf.empst.dao.EmpDAO;
import com.nf.empst.dao.impl.DeptDAOImpl;
import com.nf.empst.dao.impl.EmpDAOImpl;
import com.nf.empst.entity.Employee;
import com.nf.empst.util.CommonUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.RequestAware;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class EmpAction extends ActionSupport implements ModelDriven<Employee>, RequestAware {

    // /test.php
    public String test() throws IOException {
        request.put("xxx", empDAO.getAll());
        return "jsonsuccess";
    }

    // 声明用到的 DAO
    private Map<String, Object> request;
    private EmpDAO empDAO = new EmpDAOImpl();
    private DeptDAO deptDAO = new DeptDAOImpl();

    // 声明变量 for ModelDriven
    private Employee employee = new Employee();

    @Override
    public Employee getModel() {
        return employee;
    }


    /**
     * 员工列表，处理来自 emplist.action 的请求
     */
    private String ename, lowsal, hisal;  // 接收参数

    public String emplist() throws Exception {
        if (CommonUtil.notempty(employee.getName())) {
            request.put("emps", empDAO.queryByEname(employee.getName()));
        } else if (CommonUtil.notallempty(ename, lowsal, hisal)) {
            request.put("emps", empDAO.criteriaByConditions(ename, lowsal, hisal));
        } else {
            request.put("emps", empDAO.getAll());
        }
        request.put("depts", deptDAO.getAll());

        return "success";
    }


    /**
     * 员工保存添加模块
     */
    public void validateEmpsave() {
        // 1. 验证用户名
        String name = employee.getName();
        if (StringUtils.isEmpty(name) || name.length() > 10) {
            addFieldError("name", "姓名不能为空并且必须小于10位");
        }

        // 2. 验证工资
        Float salary = employee.getSalary();
        if(salary < 500F || salary > 50000F) {
            addFieldError("salary", "工资输入不对");
        }

        // 3. 验证日期
        Date hireDate = employee.getHireDate();
        if (hireDate == null || hireDate.after(new Date())) {
            addFieldError("hireDate", "时间输入无效");
        }

        if (hasErrors()) {
            // 返回 input 页面，提醒错误。
            request.put("depts", new DeptDAOImpl().getAll());
        }
    }

    public String empsave() {
        empDAO.persist(employee);
        return SUCCESS;
    }


    /*
    // 变量声明
    private String ename;
    private Long deptno;
    private float salary;
    private Date hireDate;

    // validateXxx 方法，会在 xxx 方法执行前执行，
    // 它用来在调用 xxx 方法前，对用户参数进行验证：
    //    1. 类型转换验证
    //    2. 用户验证
    // 只要在这个阶段，产生任何验证错误，xxx 方法就不会继续执行
    // 而是返回到 result 名字为 "input" 所代表的页面
    //
    // 需要注意，如果让验证生效，需要实现 validation 接口，
    // 一般情况下，我们让自己的 Action 继承 ActionSupport 类就可以了。
    //
    // 另外，除了 validateXxx() 方法之外，还可以实现 validate() 方法
    // 这个 validate() 方法是全局的，也就是在这个 Action 类中，任何方法执行前都会被执行。
    //
    // 所以，总体顺序：
    //   用户请求 --> validate() --> validateXxx() --> xxx() --> JSP 渲染
    public void validateEmpsave() {

        // 在进行手动验证前，struts 会进行类型转换的验证
        // 可以通过 hasErrors() 等方法查看是否存在错误

        // 除了隐式的类型转换验证，我们也可以对字段进行手动验证

        // 在验证阶段，只要存在任何错误(hasErrors)，
        // Empsave 都不会执行，而且页面会跳转到 input 对应页面

        // 下面是添加手动验证的例子
        // 1. 姓名验证
        if (employee.getName() == null || employee.getName().trim().length() == 0 || employee.getName().trim().length() > 10) {
            addFieldError("name", "姓名不能为空并且必须小于10位");
        }
        // 2. 工资验证

        // 3. 日期验证，日期不能大于今天
        if (employee.getHireDate() != null && employee.getHireDate().after(new Date())) {
            addFieldError("hireDate", "您太超前了"); // hireDate 对应 input 页面中的 hireDate 字段
        }


        // 如果出错，返回 /view/emplist.jsp 页面
        // 因为这个页面的表单中需要部门的下拉菜单数据，所以我们需要将其加载
        // 另外，在 input 页面的 head 里面，添加 <s:head /> 会添加错误信息的默认 css 类
        if (hasErrors()) {
            departments = new DeptDAOImpl().getAll();
        }
    }

    public String empsave0() throws Exception {
        Department d = new Department();
        d.setDeptno(deptno);
        empDAO.persist(new Employee(ename, salary, hireDate, d));

        return "success";
    }
    */


    /**
     * 删除员工模块
     */
    public String empdel() throws Exception {
        empDAO.remove(employee.getEmpno());
        return "success";
    }


    @Override
    public void setRequest(Map<String, Object> request) {
        this.request = request;
    }

    ////////////// getter/setter ///////////
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getLowsal() {
        return lowsal;
    }

    public void setLowsal(String lowsal) {
        this.lowsal = lowsal;
    }

    public String getHisal() {
        return hisal;
    }

    public void setHisal(String hisal) {
        this.hisal = hisal;
    }
}