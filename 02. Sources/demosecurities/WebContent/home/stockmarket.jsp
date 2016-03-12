<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<table id="stock-market" class="table table-bordered">
	<thead>
		<tr>
			<th rowspan="2">Mã CK</th>
			<th rowspan="2" class="referen">Trần</th>
			<th rowspan="2" class="referen">Sàn</th>
			<th rowspan="2" class="referen">TC</th>
			<th colspan="6">Dư mua</th>
			<th rowspan="2" class="referen">Giá<br>khớp
			</th>
			<th rowspan="2" class="referen">KL<br>khớp
			</th>
			<th rowspan="2" class="referen">+/-</th>
			<th colspan="6">Dư bán</th>
			<th rowspan="2" class="referen">Tổng<br>KL
			</th>
			<th rowspan="2" class="referen">Trung<br>bình
			</th>
			<th rowspan="2" class="referen">Mở<br>cửa
			</th>
			<th rowspan="2" class="referen">Cao<br>nhất
			</th>
			<th rowspan="2" class="referen">Thấp<br>nhất
			</th>
			<th rowspan="2" class="referen">NN<br>mua
			</th>
			<th rowspan="2" class="referen">NN<br>bán
			</th>
		</tr>
		<tr>
			<th>Giá 3</th>
			<th>KL 3</th>
			<th>Giá 2</th>
			<th>KL 2</th>
			<th>Giá 1</th>
			<th>KL 1</th>
			<th>Giá 1</th>
			<th>KL 1</th>
			<th>Giá 2</th>
			<th>KL 2</th>
			<th>Giá 3</th>
			<th>KL 3</th>
		</tr>
	</thead>
	<tbody id="list-user-body">
		<tr>
			<td class="stock-type"><label class="tock-name">ACM</label> <i
				class="fa fa-caret-up status green"></i></td>
			<td class="max_price referen">5</td>
			<td class="min_price referen">4.2</td>
			<td class="tc_price referen">4.6</td>
			<td class="sell_price_3">5</td>
			<td class="sell_weigth_3">37800</td>
			<td class="sell_price_2">4.7</td>
			<td class="sell_weigth_2">700</td>
			<td class="sell_price_1">4.3</td>
			<td class="sell_weigth_1">5000</td>
			<td class="match_price referen">4.4</td>
			<td class="match_weight referen">8100</td>
			<td class="match_value referen">-0.2</td>
			<td class="buy_price_1">4.9</td>
			<td class="buy_weigth_1">8100</td>
			<td class="buy_price_2">4.7</td>
			<td class="buy_weigth_2">21900</td>
			<td class="buy_price_3">4.8</td>
			<td class="buy_weigth_3">347600</td>
			<td class="referen">754980</td>
			<td class="referen"></td>
			<td class="total_price_1 referen">4.7</td>
			<td class="total_price_2 referen">4.8</td>
			<td class="total_price_3 referen">4.6</td>
			<td class="referen"></td>
			<td class="referen"></td>
		</tr>
		<tr>
			<td class="stock-type"><label class="tock-name">HHS</label> <i
				class="fa fa-caret-down status red"></i></td>
			<td class="max_price referen">5</td>
			<td class="min_price referen">4.2</td>
			<td class="tc_price referen">4.6</td>
			<td class="sell_price_3">4.7</td>
			<td class="sell_weigth_3">37800</td>
			<td class="sell_price_2">4.6</td>
			<td class="sell_weigth_2">700</td>
			<td class="sell_price_1">4.9</td>
			<td class="sell_weigth_1">5000</td>
			<td class="match_price referen">4.6</td>
			<td class="match_weight referen">8100</td>
			<td class="match_value referen"></td>
			<td class="buy_price_1">4.9</td>
			<td class="buy_weigth_1">8100</td>
			<td class="buy_price_2">4.7</td>
			<td class="buy_weigth_2">21900</td>
			<td class="buy_price_3">4.8</td>
			<td class="buy_weigth_3">347600</td>
			<td class="referen">754980</td>
			<td class="referen"></td>
			<td class="total_price_1 referen">4.7</td>
			<td class="total_price_2 referen">4.8</td>
			<td class="total_price_3 referen">4.6</td>
			<td class="referen"></td>
			<td class="referen"></td>
		</tr><tr>
			<td class="stock-type"><label class="tock-name">AFC</label> <i
				class="fa fa-caret-up status green"></i></td>
			<td class="max_price referen">5</td>
			<td class="min_price referen">4.2</td>
			<td class="tc_price referen">4.6</td>
			<td class="sell_price_3">5</td>
			<td class="sell_weigth_3">37800</td>
			<td class="sell_price_2">4.7</td>
			<td class="sell_weigth_2">700</td>
			<td class="sell_price_1">4.3</td>
			<td class="sell_weigth_1">5000</td>
			<td class="match_price referen">4.4</td>
			<td class="match_weight referen">8100</td>
			<td class="match_value referen">-0.2</td>
			<td class="buy_price_1">4.9</td>
			<td class="buy_weigth_1">8100</td>
			<td class="buy_price_2">4.7</td>
			<td class="buy_weigth_2">21900</td>
			<td class="buy_price_3">4.8</td>
			<td class="buy_weigth_3">347600</td>
			<td class="referen">754980</td>
			<td class="referen"></td>
			<td class="total_price_1 referen">4.7</td>
			<td class="total_price_2 referen">4.8</td>
			<td class="total_price_3 referen">4.6</td>
			<td class="referen"></td>
			<td class="referen"></td>
		</tr>
	</tbody>
</table>