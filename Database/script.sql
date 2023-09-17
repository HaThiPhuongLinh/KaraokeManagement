USE [KRManagement]
GO
/****** Object:  Table [dbo].[CTDichVu]    Script Date: 9/17/2023 4:10:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CTDichVu](
	[maHoaDon] [varchar](15) NOT NULL,
	[maDichVu] [varchar](6) NOT NULL,
	[soLuongDat] [int] NOT NULL,
	[donGia] [money] NOT NULL,
 CONSTRAINT [PK_CTDichVu] PRIMARY KEY CLUSTERED 
(
	[maDichVu] ASC,
	[maHoaDon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DichVu]    Script Date: 9/17/2023 4:10:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DichVu](
	[maDichVu] [varchar](6) NOT NULL,
	[tenDichVu] [nvarchar](100) NOT NULL,
	[giaBan] [money] NOT NULL,
	[soLuongTon] [int] NOT NULL,
	[maLDV] [varchar](6) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maDichVu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 9/17/2023 4:10:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[maHoaDon] [varchar](15) NOT NULL,
	[maNhanVien] [varchar](10) NOT NULL,
	[maKH] [varchar](10) NOT NULL,
	[maPhong] [varchar](5) NOT NULL,
	[giaPhong] [money] NOT NULL,
	[ngayGioDat] [datetime] NOT NULL,
	[ngayGioTra] [datetime] NULL,
	[tinhTrangHD] [int] NOT NULL,
	[maKM] [varchar](15) NULL,
	[tongTienHD] [money] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maHoaDon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 9/17/2023 4:10:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[maKH] [varchar](10) NOT NULL,
	[hoTen] [nvarchar](100) NOT NULL,
	[gioiTinh] [bit] NULL,
	[ngaySinh] [date] NULL,
	[cmnd] [varchar](12) NOT NULL,
	[soDienThoai] [varchar](10) NULL,
 CONSTRAINT [PK__KhachHan__7A3ECFE488B8BD0E] PRIMARY KEY CLUSTERED 
(
	[maKH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoaiDichVu]    Script Date: 9/17/2023 4:10:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiDichVu](
	[maLDV] [varchar](6) NOT NULL,
	[tenLDV] [nvarchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maLDV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoaiPhong]    Script Date: 9/17/2023 4:10:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiPhong](
	[maLP] [varchar](5) NOT NULL,
	[tenLP] [nvarchar](100) NOT NULL,
	[sucChua] [int] NOT NULL,
	[giaTien] [money] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maLP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 9/17/2023 4:10:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[maNhanVien] [varchar](10) NOT NULL,
	[hoTen] [nvarchar](100) NOT NULL,
	[cmnd] [varchar](12) NOT NULL,
	[gioiTinh] [bit] NOT NULL,
	[ngaySinh] [date] NULL,
	[soDienThoai] [varchar](10) NULL,
	[chucVu] [nvarchar](100) NOT NULL,
	[mucLuong] [money] NOT NULL,
	[trangThai] [nvarchar](100) NOT NULL,
	[taiKhoan] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK__NhanVien__BDDEF20D0A3E9CBD] PRIMARY KEY CLUSTERED 
(
	[maNhanVien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Phong]    Script Date: 9/17/2023 4:10:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Phong](
	[maPhong] [varchar](5) NOT NULL,
	[tinhTrang] [int] NOT NULL,
	[viTri] [nvarchar](100) NOT NULL,
	[maLP] [varchar](5) NOT NULL,
	[gioDatTruoc] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[maPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 9/17/2023 4:10:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[taiKhoan] [nvarchar](50) NOT NULL,
	[matKhau] [nvarchar](50) NOT NULL,
	[tinhTrang] [bit] NOT NULL,
 CONSTRAINT [PK_TaiKhoan] PRIMARY KEY CLUSTERED 
(
	[taiKhoan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[TaiKhoan] ([taiKhoan], [matKhau], [tinhTrang]) VALUES (N'nguyendinhduong', N'321', 0)
INSERT [dbo].[TaiKhoan] ([taiKhoan], [matKhau], [tinhTrang]) VALUES (N'nguyenquangduy', N'1234', 0)
INSERT [dbo].[TaiKhoan] ([taiKhoan], [matKhau], [tinhTrang]) VALUES (N'phuonglinh', N'123456', 1)
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__NhanVien__B4C453187BD25935]    Script Date: 9/17/2023 4:10:33 PM ******/
ALTER TABLE [dbo].[NhanVien] ADD  CONSTRAINT [UQ__NhanVien__B4C453187BD25935] UNIQUE NONCLUSTERED 
(
	[taiKhoan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[CTDichVu] ADD  CONSTRAINT [DF__CTDichVu__soLuon__3C69FB99]  DEFAULT ((1)) FOR [soLuongDat]
GO
ALTER TABLE [dbo].[CTDichVu] ADD  CONSTRAINT [DF__CTDichVu__donGia__3E52440B]  DEFAULT ((0)) FOR [donGia]
GO
ALTER TABLE [dbo].[DichVu] ADD  DEFAULT ((0)) FOR [giaBan]
GO
ALTER TABLE [dbo].[DichVu] ADD  DEFAULT ((0)) FOR [soLuongTon]
GO
ALTER TABLE [dbo].[HoaDon] ADD  DEFAULT (getdate()) FOR [ngayGioDat]
GO
ALTER TABLE [dbo].[HoaDon] ADD  DEFAULT ((0)) FOR [tinhTrangHD]
GO
ALTER TABLE [dbo].[LoaiPhong] ADD  DEFAULT ((0)) FOR [sucChua]
GO
ALTER TABLE [dbo].[LoaiPhong] ADD  DEFAULT ((0)) FOR [giaTien]
GO
ALTER TABLE [dbo].[NhanVien] ADD  CONSTRAINT [DF__NhanVien__chucVu__267ABA7A]  DEFAULT (N'Nhân Viên') FOR [chucVu]
GO
ALTER TABLE [dbo].[NhanVien] ADD  CONSTRAINT [DF__NhanVien__mucLuo__276EDEB3]  DEFAULT ((0)) FOR [mucLuong]
GO
ALTER TABLE [dbo].[NhanVien] ADD  CONSTRAINT [DF__NhanVien__trangT__286302EC]  DEFAULT (N'Đang làm') FOR [trangThai]
GO
ALTER TABLE [dbo].[Phong] ADD  DEFAULT ((0)) FOR [tinhTrang]
GO
ALTER TABLE [dbo].[CTDichVu]  WITH CHECK ADD  CONSTRAINT [FK_CTDichVu_DichVu] FOREIGN KEY([maDichVu])
REFERENCES [dbo].[DichVu] ([maDichVu])
GO
ALTER TABLE [dbo].[CTDichVu] CHECK CONSTRAINT [FK_CTDichVu_DichVu]
GO
ALTER TABLE [dbo].[CTDichVu]  WITH CHECK ADD  CONSTRAINT [FK_CTDichVu_HoaDon] FOREIGN KEY([maHoaDon])
REFERENCES [dbo].[HoaDon] ([maHoaDon])
GO
ALTER TABLE [dbo].[CTDichVu] CHECK CONSTRAINT [FK_CTDichVu_HoaDon]
GO
ALTER TABLE [dbo].[DichVu]  WITH CHECK ADD  CONSTRAINT [FK_DichVu_LoaiDichVu] FOREIGN KEY([maLDV])
REFERENCES [dbo].[LoaiDichVu] ([maLDV])
GO
ALTER TABLE [dbo].[DichVu] CHECK CONSTRAINT [FK_DichVu_LoaiDichVu]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon_KhachHang] FOREIGN KEY([maKH])
REFERENCES [dbo].[KhachHang] ([maKH])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_KhachHang]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon_NhanVien] FOREIGN KEY([maNhanVien])
REFERENCES [dbo].[NhanVien] ([maNhanVien])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_NhanVien]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon_Phong] FOREIGN KEY([maPhong])
REFERENCES [dbo].[Phong] ([maPhong])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_Phong]
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD  CONSTRAINT [FK_NhanVien_TaiKhoan] FOREIGN KEY([taiKhoan])
REFERENCES [dbo].[TaiKhoan] ([taiKhoan])
GO
ALTER TABLE [dbo].[NhanVien] CHECK CONSTRAINT [FK_NhanVien_TaiKhoan]
GO
ALTER TABLE [dbo].[Phong]  WITH CHECK ADD  CONSTRAINT [FK_Phong_LoaiPhong] FOREIGN KEY([maLP])
REFERENCES [dbo].[LoaiPhong] ([maLP])
GO
ALTER TABLE [dbo].[Phong] CHECK CONSTRAINT [FK_Phong_LoaiPhong]
GO
ALTER TABLE [dbo].[CTDichVu]  WITH CHECK ADD  CONSTRAINT [CK__CTDichVu__donGia__3F466844] CHECK  (([donGia]>=(0)))
GO
ALTER TABLE [dbo].[CTDichVu] CHECK CONSTRAINT [CK__CTDichVu__donGia__3F466844]
GO
ALTER TABLE [dbo].[CTDichVu]  WITH CHECK ADD  CONSTRAINT [CK__CTDichVu__soLuon__3D5E1FD2] CHECK  (([soLuongDat]>(0)))
GO
ALTER TABLE [dbo].[CTDichVu] CHECK CONSTRAINT [CK__CTDichVu__soLuon__3D5E1FD2]
GO
ALTER TABLE [dbo].[DichVu]  WITH CHECK ADD CHECK  (([giaBan]>=(0)))
GO
ALTER TABLE [dbo].[DichVu]  WITH CHECK ADD CHECK  (([giaBan]>=(0)))
GO
ALTER TABLE [dbo].[DichVu]  WITH CHECK ADD CHECK  (([soLuongTon]>=(0)))
GO
ALTER TABLE [dbo].[DichVu]  WITH CHECK ADD CHECK  (([soLuongTon]>=(0)))
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD CHECK  (([giaPhong]>=(0)))
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD CHECK  (([giaPhong]>=(0)))
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD CHECK  (([tongTienHD]>=(0)))
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD CHECK  (([tongTienHD]>=(0)))
GO
ALTER TABLE [dbo].[LoaiPhong]  WITH CHECK ADD CHECK  (([giaTien]>=(0)))
GO
ALTER TABLE [dbo].[LoaiPhong]  WITH CHECK ADD CHECK  (([giaTien]>=(0)))
GO
ALTER TABLE [dbo].[LoaiPhong]  WITH CHECK ADD CHECK  (([sucChua]>(0)))
GO
ALTER TABLE [dbo].[LoaiPhong]  WITH CHECK ADD CHECK  (([sucChua]>(0)))
GO
