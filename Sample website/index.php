<?php
ini_set("allow_url_fopen", 1);
?>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Antminer S19j Status Panel</title>
    <link rel="stylesheet" href="./style.css">
    <link rel="icon" href="./favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  </head>
  <body>
    <main>
        Loc1
        <table class="table">
            <thead>
                <tr>
                <th scope="col">IP</th>
                <th scope="col">Model</th>
                <th scope="col">Temperature</th>
                <th scope="col">Hashrate (5sec)</th>
                <th scope="col">Uptime</th>
                </tr>
            </thead>
            <tbody>
<?php
$url = 'http://localhost:8081/data?port=4028&ip=';

$ipArray = array('192.168.1.50', '192.168.1.51', '192.168.1.52', '192.168.1.55', '192.168.1.57', '192.168.1.58', '192.168.1.59', '192.168.1.60', '192.168.1.70');

foreach($ipArray as $ip)
{
    $json = file_get_contents($url.$ip);
    $obj = json_decode($json);
    $output = sprintf('%02dh:%02dm:%02ds', ($obj->uptime/ 3600),($obj->uptime/ 60 % 60), $obj->uptime% 60);
    echo "<tr>
    <td>$obj->ip</td>
    <td>$obj->model</td>
    <td>[".$obj->chipTemp[0].",".$obj->chipTemp[0].",".$obj->chipTemp[0]."]</td>
    <td>$obj->hashrate5s</td>
    <td>$output</td>
    </tr>";
}
?>
  </tbody>
</table>
</main>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  </body>
</html>
