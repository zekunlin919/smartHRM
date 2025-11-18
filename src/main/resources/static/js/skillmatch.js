/* ==========  全局变量  ========== */
let skillOptions = []; // [{id, skillName}, ...]
let projectMap = {}; // {projId: projName}
let depMap = {};     // {depId: depName}

/* ==========  初始化  ========== */
window.onload = function(){
    loadMetaData();   // 拉技能/项目/部门
    addFilterRow();   // 默认留一行
};

/* ==========  加载元数据  ========== */
function loadMetaData(){
    // ① 技能下拉
    fetch('/skillmatch/skills')
        .then(r=>r.json())
        .then(data=> skillOptions = data );
    // ② 项目 map
    fetch('/skillmatch/projects')
        .then(r=>r.json())
        .then(list=> list.forEach(p=> projectMap[p.id] = p.projName) );
    // ③ 部门 map
    fetch('/skillmatch/departments')
        .then(r=>r.json())
        .then(list=> list.forEach(d=> depMap[d.id] = d.depName) );
}

/* ==========  动态增删筛选行（重写）  ========== */
function addFilterRow(){
    const container = document.getElementById('filterContainer');
    const addBtn    = document.getElementById('addBtn');

    // ① 拉技能（无数据时不生成）
    fetch('/skillmatch/skills')
        .then(r=>r.json())
        .then(skills=>{
            if(!skills || skills.length===0) return;   // 没技能不生成

            const currentRows = container.querySelectorAll('.filter-row').length;
            if(currentRows >= skills.length){          // 达到上限
                addBtn.style.display = 'none';         // 隐藏增加按钮
                return;
            }

            const row       = document.createElement('div');
            row.className   = 'filter-row';

            // ② 技能下拉（有效 option）
            const selSkill = document.createElement('select');
            selSkill.name  = 'skill';
            selSkill.innerHTML = skills.map(s=>`<option value="${s._id}">${s.skillName}</option>`).join('');

            // ③ 熟练度滑块（保持你原有结构）
            const slider = document.createElement('input');
            slider.type = 'range'; slider.min = 1; slider.max = 5; slider.value = 3;
            slider.name = 'level'; slider.style.width='100px';
            const levelLabel = document.createElement('span');
            levelLabel.textContent = slider.value;
            slider.oninput = ()=> levelLabel.textContent = slider.value;

            // ④ 删除按钮（保持你原有结构）
            const btnDel = document.createElement('button');
            btnDel.type='button'; btnDel.textContent='删除'; btnDel.className='btn-small';
            btnDel.onclick = ()=> {
                row.remove();
                addBtn.style.display = 'inline';   // 恢复增加按钮
            };

            // ⑤ 拼装一行
            row.append(selSkill, ' 熟练度≥', slider, levelLabel, btnDel);
            container.appendChild(row);

            // ⑥ 刚加满时隐藏按钮
            if(container.querySelectorAll('.filter-row').length >= skills.length){
                addBtn.style.display = 'none';
            }
        });
}

/* ==========  搜索  ========== */
function doSearch(){
    // 拼字符串 1:3,2:5 ...
    const rows = Array.from(document.querySelectorAll('.filter-row'));
    // 拼字符串前，先过滤空值
    const reqArr = rows
        .map(r => {
            const sid = r.querySelector('select[name=skill]').value;
            const lvl = r.querySelector('input[name=level]').value;
            console.log('raw skill=', sid, 'level=', lvl);   // ← 看控制台
            // 只要有一个空，就返回 null，后面统一过滤
            return (sid !== "" && sid !== "undefined" && lvl !== "") ? sid + ':' + lvl : null;
        })
        .filter(Boolean);   // 去掉 null
    if(reqArr.length===0) return alert('请至少添加一条筛选条件');

    // POST 调用
    fetch('/skillmatch/', {
        method: 'POST',
        headers: {'Content-Type':'application/x-www-form-urlencoded'},
        body: 'requiredSkills=' + encodeURIComponent(reqArr.join(','))
    })
        .then(r=>r.json())
        .then(renderTable)
        .catch(err=>alert(err));
}

/* ==========  渲染结果表  ========== */
function renderTable(list){
    const tbody = document.querySelector('#resultTable tbody');
    tbody.innerHTML = '';   // 清空旧数据

    list.forEach(emp=>{
        const tr = document.createElement('tr');

        // 技能列：拼成 "Java-4, MySQL-3"
        const skillTexts = (emp.skillList || [])
            .map(item => {
                const skill = skillOptions.find(s => s._id == item.skillId);
                const name  = skill ? skill.skillName : '未知技能';
                return `${name} - 熟练度:${item.proficiency}`;
            })
            .join('<br>');

        // 项目列：拼成 "订单系统, 支付中心"
        const projTexts = (emp.projects || [])
            .map(item => projectMap[item.projId] || `项目${item.projId}`)
            .join('<br>');

        tr.innerHTML = `
            <td>${emp._id}</td>
            <td>${emp.empName}</td>
            <td>${depMap[emp.depId] || '未知部门'}</td>
            <td>${skillTexts}</td>
            <td>${projTexts}</td>
        `;
        tbody.appendChild(tr);
    });
}