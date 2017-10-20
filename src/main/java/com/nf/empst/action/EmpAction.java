package com.nf.empst.action;

import com.nf.empst.dao.DeptDAO;
import com.nf.empst.dao.EmpDAO;
import com.nf.empst.dao.impl.DeptDAOImpl;
import com.nf.empst.dao.impl.EmpDAOImpl;
import com.nf.empst.entity.Department;
import com.nf.empst.entity.Employee;
import com.nf.empst.util.CommonUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.util.Date;
import java.util.List;

public class EmpAction extends ActionSupport implements ModelDriven<Employee> {

    // 声明用到的 DAO
    private EmpDAO empDAO = new EmpDAOImpl();
    private DeptDAO deptDAO = new DeptDAOImpl();

    /**
     * 员工列表模块
     */
    // 用来接收请求中的参数
    private String ename1, ename2, lowsal, hisal;

    // 用来保存返回给 jsp 的数据
    private List<Employee> employees;
    private List<Department> departments;

    // 处理来自 emplist.action 的请求
    public String emplist() throws Exception {
        if (CommonUtil.notempty(ename1)) {
            employees = empDAO.queryByEname(ename1);
        } else if (CommonUtil.notallempty(ename2, lowsal, hisal)) {
            employees = empDAO.criteriaByConditions(ename2, lowsal, hisal);
        } else {
            employees = empDAO.getAll();
        }
        departments = deptDAO.getAll();
        return "success";
    }



    /**
     * 员工保存添加模块
     */
    // 变量定义
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

    // 正式的代码执行函数
    public String empsave0() throws Exception {
        Department d = new Department();
        d.setDeptno(deptno);
        empDAO.persist(new Employee(ename, salary, hireDate, d));

        return "success";
    }

    private Employee employee;
    public String empsave() {
        empDAO.persist(employee);
        return SUCCESS;
    }



    /**
     * 删除员工模块
     */
    private long empno2;

    public String empdel() throws Exception {
        empDAO.remove(empno2);
        return "success";
    }


    ////////////// getter/setter ///////////

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getEname1() {
        return ename1;
    }

    public void setEname1(String ename1) {
        this.ename1 = ename1;
    }

    public String getEname2() {
        return ename2;
    }

    public void setEname2(String ename2) {
        this.ename2 = ename2;
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

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Long getDeptno() {
        return deptno;
    }

    public void setDeptno(Long deptno) {
        this.deptno = deptno;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public long getEmpno2() {
        return empno2;
    }

    public void setEmpno2(long empno2) {
        this.empno2 = empno2;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public Employee getModel() {
        if(employee == null) {
            employee = new Employee();
        }
        return employee;
    }
}
