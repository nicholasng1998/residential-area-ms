<#assign currentDate = .now?datetime>

<html>
<head>
    <title>Monthly Statement</title>
    <style>
      table {
        border-collapse: collapse;
        width: 100%;
      }

      th, td {
        border: 1px solid black;
        padding: 8px;
        text-align: left;
      }
    </style>
</head>
<body>
    <h1>Statement</h1>

    <h1>Complete Payment List</h1>
    <table>
      <tr>
        <th>Resident ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Phone No</th>
        <th>Unit No</th>
      </tr>

        <#list complete as row>
          <tr>
            <td>${row.residentId}</td>
            <td>${row.name}</td>
            <td>${row.email}</td>
            <td>${row.phoneNo}</td>
            <td>${row.unitNo}</td>
          </tr>
        </#list>
    </table>

        <h1>Pending Payment List</h1>
        <table>
          <tr>
            <th>Resident ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone No</th>
            <th>Unit No</th>
          </tr>

            <#list pending as row>
              <tr>
                <td>${row.residentId}</td>
                <td>${row.name}</td>
                <td>${row.email}</td>
                <td>${row.phoneNo}</td>
                <td>${row.unitNo}</td>
              </tr>
            </#list>
        </table>

        <p>Generated on: ${currentDate?string("yyyy-MM-dd")}</p>
</body>
</html>
