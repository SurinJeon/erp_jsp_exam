-- 사원
CREATE TABLE employee (
	emp_no INT NOT null, -- 사원번호,
	emp_name VARCHAR(20) NOT null, -- 사원명,
	tno INT null, -- 직책,
	manager INT null, -- 직속상사,
	salary INT null, -- 급여,
	dno INT null, -- 부서,
	hiredate DATE NULL -- 입사일
);

-- 사원
ALTER TABLE employee
	ADD CONSTRAINT PK_employee -- 사원 기본키
	PRIMARY KEY (
		emp_no -- 사원번호
	);

-- 부서
CREATE TABLE department (
	dept_no INT NOT null, -- 부서번호,
	dept_name VARCHAR(20) null, -- 부서명,
	floor INT NULL -- 위치
);

-- 부서
ALTER TABLE department
	ADD CONSTRAINT PK_department -- 부서 기본키
	PRIMARY KEY (
		dept_no -- 부서번호
	);

-- 직책
CREATE TABLE title (
	title_no INT NOT null, -- 직책번호,
	title_name VARCHAR(20) NOT NULL -- 직책명
);

-- 직책
ALTER TABLE title
	ADD CONSTRAINT PK_title -- 직책 기본키
	PRIMARY KEY (
		title_no -- 직책번호
	);

-- 사원
ALTER TABLE employee
	ADD CONSTRAINT FK_title_TO_employee -- 직책 -> 사원
	FOREIGN KEY (
		tno -- 직책
	)
	REFERENCES title ( -- 직책
		title_no -- 직책번호
	);

-- 사원
ALTER TABLE employee
	ADD CONSTRAINT FK_employee_TO_employee -- 사원 -> 사원
	FOREIGN KEY (
		manager -- 직속상사
	)
	REFERENCES employee ( -- 사원
		emp_no -- 사원번호
	);

-- 사원
ALTER TABLE employee
	ADD CONSTRAINT FK_department_TO_employee -- 부서 -> 사원
	FOREIGN KEY (
		dno -- 부서
	)
	REFERENCES department ( -- 부서
		dept_no -- 부서번호
	);