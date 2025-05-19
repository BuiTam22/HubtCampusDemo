--Create database HubtCampus;
--use HubtCampus;
-- Bảng Users: Lưu thông tin người dùng chính như tài khoản, mật khẩu, thông tin cá nhân
CREATE TABLE Users (
    UserID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của người dùng
    Username VARCHAR(50) NOT NULL UNIQUE, -- Tên đăng nhập, không được trùng
    Email VARCHAR(100) NOT NULL UNIQUE, -- Email, không được trùng
    Password VARCHAR(255) NOT NULL, -- Mật khẩu đã được mã hóa
    FullName NVARCHAR(100) NOT NULL, -- Họ tên đầy đủ
    DateOfBirth DATE, -- Ngày sinh
    School NVARCHAR(255), -- Trường học
    Role VARCHAR(20) DEFAULT 'STUDENT', -- Vai trò: học sinh, giáo viên hoặc admin
    Status VARCHAR(20) DEFAULT 'ONLINE', -- Trạng thái hoạt động
    AccountStatus VARCHAR(20) DEFAULT 'ACTIVE', -- Trạng thái tài khoản
    Image VARCHAR(255), -- Đường dẫn ảnh đại diện
    Bio NVARCHAR(500), -- Tiểu sử/giới thiệu
    Provider VARCHAR(20) DEFAULT 'local', -- Phương thức đăng nhập (local/google/facebook...)
    ProviderID VARCHAR(100), -- ID từ nhà cung cấp đăng nhập
    EmailVerified BIT DEFAULT 0, -- Đánh dấu email đã xác thực chưa
    PhoneNumber VARCHAR(15), -- Số điện thoại
    Address NVARCHAR(255), -- Địa chỉ
    City NVARCHAR(100), -- Thành phố
    Country NVARCHAR(100), -- Quốc gia
    LastLoginIP VARCHAR(45), -- IP đăng nhập gần nhất
    CreatedAt DATETIME DEFAULT GETDATE(), -- Thời điểm tạo tài khoản
    UpdatedAt DATETIME DEFAULT GETDATE(), -- Thời điểm cập nhật gần nhất
    LastLoginAt DATETIME, -- Thời điểm đăng nhập gần nhất
    DeletedAt DATETIME, -- Thời điểm xóa tài khoản
    CONSTRAINT CHK_User_Role CHECK (Role IN ('STUDENT', 'TEACHER', 'ADMIN')), -- Kiểm tra vai trò hợp lệ
    CONSTRAINT CHK_User_Status CHECK (Status IN ('ONLINE', 'OFFLINE', 'AWAY')), -- Kiểm tra trạng thái hợp lệ
    CONSTRAINT CHK_Account_Status CHECK (AccountStatus IN ('ACTIVE', 'LOCKED', 'SUSPENDED', 'DELETED')) -- Kiểm tra trạng thái tài khoản hợp lệ
);
GO

-- Bảng UserProfiles: Lưu thông tin bổ sung của người dùng như học vấn, kinh nghiệm làm việc
CREATE TABLE UserProfiles (
    ProfileID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của profile
    UserID BIGINT UNIQUE FOREIGN KEY REFERENCES Users(UserID), -- Liên kết với bảng Users
    Education NVARCHAR(MAX), -- Thông tin học vấn
    WorkExperience NVARCHAR(MAX), -- Kinh nghiệm làm việc
    Skills NVARCHAR(MAX), -- Kỹ năng
    Interests NVARCHAR(MAX), -- Sở thích
    SocialLinks NVARCHAR(MAX), -- Liên kết mạng xã hội dạng JSON
    Achievements NVARCHAR(MAX), -- Thành tích và huy hiệu dạng JSON
    PreferredLanguage VARCHAR(10) DEFAULT 'vi', -- Ngôn ngữ ưa thích
    TimeZone VARCHAR(50) DEFAULT 'Asia/Ho_Chi_Minh', -- Múi giờ
    NotificationPreferences NVARCHAR(MAX), -- Cài đặt thông báo dạng JSON
    UpdatedAt DATETIME DEFAULT GETDATE() -- Thời điểm cập nhật gần nhất
);
GO

-- Bảng UserRankings: Quản lý xếp hạng và thành tích của người dùng
CREATE TABLE UserRankings (
    RankingID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của xếp hạng
    UserID BIGINT FOREIGN KEY REFERENCES Users(UserID), -- Liên kết với bảng Users
    Tier VARCHAR(20), -- Hạng: Master, Diamond, Platinum, Gold, Silver, Bronze
    TotalPoints INT DEFAULT 0, -- Tổng điểm
    EventPoints INT DEFAULT 0, -- Điểm từ sự kiện
    CoursePoints INT DEFAULT 0, -- Điểm từ khóa học
    ProblemsSolved INT DEFAULT 0, -- Số bài tập đã giải
    Accuracy DECIMAL(5,2) DEFAULT 0, -- Độ chính xác
    Wins INT DEFAULT 0, -- Số lần chiến thắng
    MonthlyScore INT DEFAULT 0, -- Điểm tháng
    WeeklyScore INT DEFAULT 0, -- Điểm tuần
    LastCalculatedAt DATETIME DEFAULT GETDATE(), -- Thời điểm tính toán gần nhất
    CONSTRAINT CHK_Ranking_Tier CHECK (Tier IN ('MASTER', 'DIAMOND', 'PLATINUM', 'GOLD', 'SILVER', 'BRONZE')) -- Kiểm tra hạng hợp lệ
);
GO

-- Bảng Achievements: Quản lý các loại thành tích và huy hiệu có thể đạt được
CREATE TABLE Achievements (
    AchievementID INT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của thành tích
    Name NVARCHAR(100) NOT NULL, -- Tên thành tích
    Description NVARCHAR(500), -- Mô tả thành tích
    Type VARCHAR(50), -- Loại thành tích
    Icon VARCHAR(255), -- Đường dẫn icon
    Points INT DEFAULT 0, -- Điểm thưởng
    Criteria NVARCHAR(MAX), -- Tiêu chí đạt thành tích dạng JSON
    CreatedAt DATETIME DEFAULT GETDATE() -- Thời điểm tạo
);
GO

-- Bảng UserAchievements: Lưu trữ thành tích đã đạt được của người dùng
CREATE TABLE UserAchievements (
    UserAchievementID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng
    UserID BIGINT FOREIGN KEY REFERENCES Users(UserID), -- Liên kết với bảng Users
    AchievementID INT FOREIGN KEY REFERENCES Achievements(AchievementID), -- Liên kết với bảng Achievements
    EarnedAt DATETIME DEFAULT GETDATE(), -- Thời điểm đạt được
    Progress INT DEFAULT 0, -- Tiến độ hoàn thành
    CONSTRAINT UQ_User_Achievement UNIQUE (UserID, AchievementID) -- Đảm bảo không trùng lặp thành tích
);
GO

-- Bảng Posts: Quản lý bài đăng của người dùng
CREATE TABLE Posts (
    PostID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của bài đăng
    UserID BIGINT FOREIGN KEY REFERENCES Users(UserID), -- Người đăng
    Content NVARCHAR(MAX), -- Nội dung bài đăng
    Type VARCHAR(20) DEFAULT 'regular', -- Loại bài đăng
    Visibility VARCHAR(20) DEFAULT 'public', -- Quyền xem
    Location NVARCHAR(255), -- Vị trí đăng
    CreatedAt DATETIME DEFAULT GETDATE(), -- Thời điểm tạo
    UpdatedAt DATETIME DEFAULT GETDATE(), -- Thời điểm cập nhật
    DeletedAt DATETIME, -- Thời điểm xóa
    LikesCount INT DEFAULT 0, -- Số lượt thích
    CommentsCount INT DEFAULT 0, -- Số bình luận
    SharesCount INT DEFAULT 0, -- Số lượt chia sẻ
    ReportsCount INT DEFAULT 0, -- Số lượt báo cáo
    CONSTRAINT CHK_Post_Type CHECK (Type IN ('regular', 'article', 'question', 'announcement')), -- Kiểm tra loại bài đăng hợp lệ
    CONSTRAINT CHK_Post_Visibility CHECK (Visibility IN ('public', 'private', 'friends')) -- Kiểm tra quyền xem hợp lệ
);
GO

-- Bảng PostMedia: Lưu trữ media (ảnh, video) của bài đăng
CREATE TABLE PostMedia (
    MediaID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của media
    PostID BIGINT FOREIGN KEY REFERENCES Posts(PostID), -- Liên kết với bài đăng
    MediaUrl VARCHAR(255) NOT NULL, -- Đường dẫn media
    MediaType VARCHAR(20), -- Loại media
    ThumbnailUrl VARCHAR(255), -- Đường dẫn ảnh thu nhỏ
    Size INT, -- Kích thước file
    Width INT, -- Chiều rộng
    Height INT, -- Chiều cao
    Duration INT, -- Thời lượng (cho video)
    CreatedAt DATETIME DEFAULT GETDATE(), -- Thời điểm tạo
    CONSTRAINT CHK_Media_Type CHECK (MediaType IN ('image', 'video', 'document', 'audio')) -- Kiểm tra loại media hợp lệ
);
GO

-- Bảng PostLikes: Quản lý lượt thích của bài đăng
CREATE TABLE PostLikes (
    LikeID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của lượt thích
    PostID BIGINT FOREIGN KEY REFERENCES Posts(PostID), -- Liên kết với bài đăng được thích
    UserID BIGINT FOREIGN KEY REFERENCES Users(UserID), -- Người dùng thực hiện thích
    CreatedAt DATETIME DEFAULT GETDATE(), -- Thời điểm thích
    CONSTRAINT UQ_Post_Like UNIQUE (PostID, UserID) -- Đảm bảo mỗi người chỉ thích 1 lần
);
GO

-- Bảng Comments: Quản lý bình luận và phản hồi
CREATE TABLE Comments (
    CommentID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của bình luận
    PostID BIGINT FOREIGN KEY REFERENCES Posts(PostID), -- Bài đăng được bình luận
    UserID BIGINT FOREIGN KEY REFERENCES Users(UserID), -- Người bình luận
    ParentCommentID BIGINT FOREIGN KEY REFERENCES Comments(CommentID), -- ID bình luận cha (nếu là phản hồi)
    Content NVARCHAR(MAX), -- Nội dung bình luận
    LikesCount INT DEFAULT 0, -- Số lượt thích bình luận
    RepliesCount INT DEFAULT 0, -- Số lượt phản hồi
    CreatedAt DATETIME DEFAULT GETDATE(), -- Thời điểm tạo
    UpdatedAt DATETIME, -- Thời điểm cập nhật
    DeletedAt DATETIME, -- Thời điểm xóa
    IsEdited BIT DEFAULT 0, -- Đánh dấu đã chỉnh sửa
    IsDeleted BIT DEFAULT 0 -- Đánh dấu đã xóa
);
GO

-- Bảng CommentLikes: Quản lý lượt thích của bình luận
CREATE TABLE CommentLikes (
    CommentLikeID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của lượt thích bình luận
    CommentID BIGINT FOREIGN KEY REFERENCES Comments(CommentID), -- Bình luận được thích
    UserID BIGINT FOREIGN KEY REFERENCES Users(UserID), -- Người thích bình luận
    CreatedAt DATETIME DEFAULT GETDATE(), -- Thời điểm thích
    CONSTRAINT UQ_Comment_Like UNIQUE (CommentID, UserID) -- Đảm bảo mỗi người chỉ thích 1 lần
);
GO

-- Bảng Tags: Quản lý các thẻ tag
CREATE TABLE Tags (
    TagID INT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của tag
    Name NVARCHAR(50) NOT NULL UNIQUE, -- Tên tag, không được trùng
    Description NVARCHAR(255), -- Mô tả về tag
    CreatedAt DATETIME DEFAULT GETDATE(), -- Thời điểm tạo
    UsageCount INT DEFAULT 0 -- Số lần sử dụng tag
);
GO

-- Bảng PostTags: Liên kết giữa bài đăng và thẻ tag
CREATE TABLE PostTags (
    PostID BIGINT FOREIGN KEY REFERENCES Posts(PostID), -- Bài đăng được gắn tag
    TagID INT FOREIGN KEY REFERENCES Tags(TagID), -- Tag được gắn
    CreatedAt DATETIME DEFAULT GETDATE(), -- Thời điểm gắn tag
    PRIMARY KEY (PostID, TagID) -- Khóa chính kết hợp
);
GO

-- Bảng Courses: Quản lý thông tin khóa học
CREATE TABLE Courses (
    CourseID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của khóa học
    Title NVARCHAR(255) NOT NULL, -- Tiêu đề khóa học
    Slug VARCHAR(255) UNIQUE, -- Đường dẫn thân thiện
    Description NVARCHAR(MAX), -- Mô tả chi tiết
    ShortDescription NVARCHAR(500), -- Mô tả ngắn
    InstructorID BIGINT FOREIGN KEY REFERENCES Users(UserID), -- Người hướng dẫn
    Level VARCHAR(20), -- Cấp độ khóa học
    Category VARCHAR(50), -- Danh mục chính
    SubCategory VARCHAR(50), -- Danh mục phụ
    Language VARCHAR(20) DEFAULT 'vi', -- Ngôn ngữ giảng dạy
    Duration INT, -- Thời lượng (phút)
    Capacity INT, -- Sức chứa học viên
    EnrolledCount INT DEFAULT 0, -- Số học viên đã đăng ký
    Rating DECIMAL(3,2) DEFAULT 0, -- Đánh giá trung bình
    RatingCount INT DEFAULT 0, -- Số lượt đánh giá
    Price DECIMAL(10,2) DEFAULT 0, -- Giá gốc
    DiscountPrice DECIMAL(10,2), -- Giá khuyến mãi
    ImageUrl VARCHAR(255), -- Ảnh đại diện
    VideoUrl VARCHAR(255), -- Video giới thiệu
    Requirements NVARCHAR(MAX), -- Yêu cầu đầu vào
    Objectives NVARCHAR(MAX), -- Mục tiêu khóa học
    Syllabus NVARCHAR(MAX), -- Giáo trình
    Status VARCHAR(20) DEFAULT 'draft', -- Trạng thái khóa học
    IsPublished BIT DEFAULT 0, -- Đã xuất bản chưa
    PublishedAt DATETIME, -- Thời điểm xuất bản
    CreatedAt DATETIME DEFAULT GETDATE(), -- Thời điểm tạo
    UpdatedAt DATETIME DEFAULT GETDATE(), -- Thời điểm cập nhật
    DeletedAt DATETIME, -- Thời điểm xóa
    CONSTRAINT CHK_Course_Level CHECK (Level IN ('beginner', 'intermediate', 'advanced', 'expert')), -- Kiểm tra cấp độ hợp lệ
    CONSTRAINT CHK_Course_Status CHECK (Status IN ('draft', 'review', 'published', 'archived')) -- Kiểm tra trạng thái hợp lệ
);
GO

-- Bảng CourseModules: Quản lý các module trong khóa học
CREATE TABLE CourseModules (
    ModuleID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của module
    CourseID BIGINT FOREIGN KEY REFERENCES Courses(CourseID), -- Khóa học chứa module
    Title NVARCHAR(255) NOT NULL, -- Tiêu đề module
    Description NVARCHAR(MAX), -- Mô tả module
    OrderIndex INT NOT NULL, -- Thứ tự sắp xếp
    Duration INT, -- Thời lượng (phút)
    IsPublished BIT DEFAULT 0, -- Đã xuất bản chưa
    CreatedAt DATETIME DEFAULT GETDATE(), -- Thời điểm tạo
    UpdatedAt DATETIME DEFAULT GETDATE() -- Thời điểm cập nhật
);
GO

-- Bảng CourseLessons: Quản lý các bài học trong module
CREATE TABLE CourseLessons (
    LessonID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của bài học
    ModuleID BIGINT FOREIGN KEY REFERENCES CourseModules(ModuleID), -- Module chứa bài học
    Title NVARCHAR(255) NOT NULL, -- Tiêu đề bài học
    Description NVARCHAR(MAX), -- Mô tả bài học
    Type VARCHAR(50) NOT NULL, -- Loại bài học
    Content NVARCHAR(MAX), -- Nội dung cho bài học dạng văn bản
    VideoUrl VARCHAR(255), -- Đường dẫn video
    Duration INT, -- Thời lượng (phút)
    OrderIndex INT NOT NULL, -- Thứ tự sắp xếp
    IsPreview BIT DEFAULT 0, -- Cho phép xem thử
    IsPublished BIT DEFAULT 0, -- Đã xuất bản chưa
    CreatedAt DATETIME DEFAULT GETDATE(), -- Thời điểm tạo
    UpdatedAt DATETIME DEFAULT GETDATE(), -- Thời điểm cập nhật
    CONSTRAINT CHK_Lesson_Type CHECK (Type IN ('video', 'text', 'quiz', 'assignment', 'coding', 'essay')) -- Kiểm tra loại bài học hợp lệ
);
GO

-- Bảng CourseEnrollments: Quản lý đăng ký khóa học của học viên
CREATE TABLE CourseEnrollments (
    EnrollmentID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của đăng ký
    CourseID BIGINT FOREIGN KEY REFERENCES Courses(CourseID), -- Khóa học được đăng ký
    UserID BIGINT FOREIGN KEY REFERENCES Users(UserID), -- Học viên đăng ký
    Progress INT DEFAULT 0, -- Tiến độ học tập (%)
    LastAccessedLessonID BIGINT FOREIGN KEY REFERENCES CourseLessons(LessonID), -- Bài học truy cập gần nhất
    EnrolledAt DATETIME DEFAULT GETDATE(), -- Thời điểm đăng ký
    CompletedAt DATETIME, -- Thời điểm hoàn thành
    CertificateIssued BIT DEFAULT 0, -- Đã cấp chứng chỉ chưa
    Status VARCHAR(20) DEFAULT 'active', -- Trạng thái học tập
    CONSTRAINT CHK_Enrollment_Status CHECK (Status IN ('active', 'completed', 'dropped', 'suspended')) -- Kiểm tra trạng thái hợp lệ
);
GO

-- Bảng LessonProgress: Theo dõi tiến độ học tập của học viên
CREATE TABLE LessonProgress (
    ProgressID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của tiến độ
    EnrollmentID BIGINT FOREIGN KEY REFERENCES CourseEnrollments(EnrollmentID), -- Liên kết với đăng ký khóa học
    LessonID BIGINT FOREIGN KEY REFERENCES CourseLessons(LessonID), -- Bài học đang theo dõi
    Status VARCHAR(20) DEFAULT 'not_started', -- Trạng thái học tập
    CompletedAt DATETIME, -- Thời điểm hoàn thành
    TimeSpent INT DEFAULT 0, -- Thời gian đã học (giây)
    LastPosition INT DEFAULT 0, -- Vị trí xem video gần nhất
    CONSTRAINT CHK_Lesson_Status CHECK (Status IN ('not_started', 'in_progress', 'completed')) -- Kiểm tra trạng thái hợp lệ
);
GO
-- Bảng CodingExercises: Quản lý bài tập lập trình
CREATE TABLE CodingExercises (
    ExerciseID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của bài tập
    LessonID BIGINT FOREIGN KEY REFERENCES CourseLessons(LessonID), -- Liên kết với bài học
    Title NVARCHAR(255) NOT NULL, -- Tiêu đề bài tập
    Description NVARCHAR(MAX), -- Mô tả chi tiết bài tập
    ProgrammingLanguage VARCHAR(50), -- Ngôn ngữ lập trình được sử dụng
    InitialCode NVARCHAR(MAX), -- Code mẫu ban đầu
    SolutionCode NVARCHAR(MAX), -- Code lời giải
    TestCases NVARCHAR(MAX), -- Các test case kiểm tra (định dạng JSON)
    TimeLimit INT DEFAULT 1000, -- Giới hạn thời gian chạy (mili giây)
    MemoryLimit INT DEFAULT 256, -- Giới hạn bộ nhớ sử dụng (MB)
    Difficulty VARCHAR(20) DEFAULT 'medium', -- Độ khó của bài tập
    Points INT DEFAULT 0, -- Điểm cho bài tập
    CreatedAt DATETIME DEFAULT GETDATE(), -- Thời điểm tạo
    UpdatedAt DATETIME DEFAULT GETDATE(), -- Thời điểm cập nhật
    CONSTRAINT CHK_Exercise_Difficulty CHECK (Difficulty IN ('easy', 'medium', 'hard', 'expert')) -- Kiểm tra độ khó hợp lệ
);
GO

-- tao bang de quan ly testCases de dang hon
CREATE TABLE ExerciseTestCases (
    TestCaseID BIGINT IDENTITY(1,1) PRIMARY KEY,
    ExerciseID BIGINT FOREIGN KEY REFERENCES CodingExercises(ExerciseID),
    Input NVARCHAR(MAX),
    ExpectedOutput NVARCHAR(MAX),
    IsPublic BIT DEFAULT 0, -- để phân biệt test case public/private
    Score INT DEFAULT 1 -- nếu mỗi test case có điểm riêng
);
GO

-- Bảng CodingSubmissions: Lưu trữ bài nộp của học viên
CREATE TABLE CodingSubmissions (
    SubmissionID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của bài nộp
    ExerciseID BIGINT FOREIGN KEY REFERENCES CodingExercises(ExerciseID), -- Liên kết với bài tập
    UserID BIGINT FOREIGN KEY REFERENCES Users(UserID), -- Người nộp bài
    Code NVARCHAR(MAX), -- Code đã nộp
    Language VARCHAR(50), -- Ngôn ngữ lập trình sử dụng
    InputFormat NVARCHAR(MAX), -- Mô tả định dạng đầu vào
    OutputFormat NVARCHAR(MAX); -- Mô tả định dạng đầu ra
    ConstraintName NVARCHAR(50);
    Status VARCHAR(20), -- Trạng thái chấm bài
    ExecutionTime INT, -- Thời gian chạy (mili giây)
    MemoryUsed INT, -- Bộ nhớ sử dụng (KB)
    TestCasesPassed INT DEFAULT 0, -- Số test case đã pass
    TotalTestCases INT DEFAULT 0, -- Tổng số test case
    Score INT DEFAULT 0, -- Điểm đạt được
    SubmittedAt DATETIME DEFAULT GETDATE(), -- Thời điểm nộp bài
    CONSTRAINT CHK_Submission_Status CHECK (Status IN ('pending', 'running', 'accepted', 'wrong_answer', 'time_limit', 'memory_limit', 'runtime_error')) -- Kiểm tra trạng thái hợp lệ
);
GO

-- Bảng Exams: Quản lý các kỳ thi
CREATE TABLE Exams (
    ExamID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của kỳ thi
    CourseID BIGINT FOREIGN KEY REFERENCES Courses(CourseID), -- Liên kết với khóa học
    Title NVARCHAR(255) NOT NULL, -- Tiêu đề kỳ thi
    Description NVARCHAR(MAX), -- Mô tả kỳ thi
    Type VARCHAR(50) NOT NULL, -- Loại kỳ thi   
    Duration INT NOT NULL, -- Thời gian làm bài (phút)
    TotalPoints INT DEFAULT 100, -- Tổng điểm
    PassingScore INT DEFAULT 60, -- Điểm đạt
    StartTime DATETIME NOT NULL, -- Thời gian bắt đầu
    EndTime DATETIME NOT NULL, -- Thời gian kết thúc
    Instructions NVARCHAR(MAX), -- Hướng dẫn làm bài
    AllowReview BIT DEFAULT 1, -- Cho phép xem lại bài
    ShuffleQuestions BIT DEFAULT 1, -- Xáo trộn câu hỏi
    Status VARCHAR(20) DEFAULT 'upcoming', -- Trạng thái kỳ thi
    CreatedBy BIGINT FOREIGN KEY REFERENCES Users(UserID), -- Người tạo
    CreatedAt DATETIME DEFAULT GETDATE(), -- Thời điểm tạo
    UpdatedAt DATETIME DEFAULT GETDATE(), -- Thời điểm cập nhật
    CONSTRAINT CHK_Exam_Type CHECK (Type IN ('multiple_choice', 'essay', 'coding', 'mixed')), -- Kiểm tra loại kỳ thi hợp lệ
    CONSTRAINT CHK_Exam_Status CHECK (Status IN ('upcoming', 'ongoing', 'completed', 'cancelled')) -- Kiểm tra trạng thái hợp lệ
);
GO
-- Bảng ExamQuestions: Quản lý câu hỏi trong kỳ thi
CREATE TABLE ExamQuestions (
    QuestionID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của câu hỏi
    ExamID BIGINT FOREIGN KEY REFERENCES Exams(ExamID), -- Liên kết với kỳ thi
    Type VARCHAR(50) NOT NULL, -- Loại câu hỏi
    Content NVARCHAR(MAX), -- Nội dung câu hỏi
    Points INT DEFAULT 1, -- Điểm cho câu hỏi
    OrderIndex INT, -- Thứ tự câu hỏi
    Options NVARCHAR(MAX), -- Các lựa chọn (định dạng JSON)
    CorrectAnswer NVARCHAR(MAX), -- Đáp án đúng
    Explanation NVARCHAR(MAX), -- Giải thích đáp án
    CreatedAt DATETIME DEFAULT GETDATE(), -- Thời điểm tạo
    UpdatedAt DATETIME DEFAULT GETDATE(), -- Thời điểm cập nhật
    CONSTRAINT CHK_Question_Type CHECK (Type IN ('multiple_choice', 'essay', 'coding')) -- Kiểm tra loại câu hỏi hợp lệ
);
GO

-- Bảng ExamParticipants: Quản lý người tham gia kỳ thi
CREATE TABLE ExamParticipants (
    ParticipantID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của người tham gia
    ExamID BIGINT FOREIGN KEY REFERENCES Exams(ExamID), -- Liên kết với kỳ thi
    UserID BIGINT FOREIGN KEY REFERENCES Users(UserID), -- Người tham gia
    StartedAt DATETIME, -- Thời điểm bắt đầu làm bài
    CompletedAt DATETIME, -- Thời điểm hoàn thành
    TimeSpent INT, -- Thời gian làm bài (phút)
    Score INT, -- Điểm số đạt được
    Status VARCHAR(20) DEFAULT 'registered', -- Trạng thái tham gia
    Feedback NVARCHAR(MAX), -- Phản hồi của người tham gia
    ReviewedBy BIGINT FOREIGN KEY REFERENCES Users(UserID), -- Người chấm bài
    ReviewedAt DATETIME, -- Thời điểm chấm bài
    CONSTRAINT CHK_Participant_Status CHECK (Status IN ('registered', 'in_progress', 'completed', 'reviewed')) -- Kiểm tra trạng thái hợp lệ
);
GO

-- Bảng ExamAnswers: Lưu trữ câu trả lời của thí sinh
CREATE TABLE ExamAnswers (
    AnswerID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của câu trả lời
    ParticipantID BIGINT FOREIGN KEY REFERENCES ExamParticipants(ParticipantID), -- Liên kết với người tham gia
    QuestionID BIGINT FOREIGN KEY REFERENCES ExamQuestions(QuestionID), -- Liên kết với câu hỏi
    Answer NVARCHAR(MAX), -- Câu trả lời
    IsCorrect BIT, -- Đánh dấu đúng/sai
    Score INT, -- Điểm cho câu trả lời
    ReviewerComments NVARCHAR(MAX), -- Nhận xét của người review
    SubmittedAt DATETIME DEFAULT GETDATE() -- Thời điểm nộp bài
);
GO

-- Bảng ExamMonitoringLogs: Ghi log giám sát trong kỳ thi
CREATE TABLE ExamMonitoringLogs (
    LogID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của log
    ParticipantID BIGINT FOREIGN KEY REFERENCES ExamParticipants(ParticipantID), -- Liên kết với người tham gia
    EventType VARCHAR(50), -- Loại sự kiện
    EventData NVARCHAR(MAX), -- Dữ liệu sự kiện (định dạng JSON)
    Timestamp DATETIME DEFAULT GETDATE(), -- Thời điểm ghi log
    CONSTRAINT CHK_Event_Type CHECK (EventType IN ('tab_switch', 'full_screen_exit', 'copy_paste', 'face_detection', 'multiple_faces', 'no_face', 'suspicious_activity')) -- Kiểm tra loại sự kiện hợp lệ
);
GO

-- Tạo các chỉ mục (Index) để tối ưu truy vấn
CREATE INDEX IX_Courses_Status ON Courses(Status); -- Chỉ mục theo trạng thái khóa học
CREATE INDEX IX_Courses_InstructorID ON Courses(InstructorID); -- Chỉ mục theo ID giảng viên
CREATE INDEX IX_CourseEnrollments_UserID ON CourseEnrollments(UserID); -- Chỉ mục theo ID người dùng đăng ký
CREATE INDEX IX_CourseEnrollments_Status ON CourseEnrollments(Status); -- Chỉ mục theo trạng thái đăng ký
CREATE INDEX IX_LessonProgress_Status ON LessonProgress(Status); -- Chỉ mục theo trạng thái tiến độ
CREATE INDEX IX_CodingSubmissions_Status ON CodingSubmissions(Status); -- Chỉ mục theo trạng thái nộp bài
CREATE INDEX IX_Exams_StartTime ON Exams(StartTime); -- Chỉ mục theo thời gian bắt đầu thi
CREATE INDEX IX_ExamParticipants_Status ON ExamParticipants(Status); -- Chỉ mục theo trạng thái tham gia
CREATE INDEX IX_ExamMonitoringLogs_Timestamp ON ExamMonitoringLogs(Timestamp); -- Chỉ mục theo thời gian log
GO 

-- Bảng Stories: Quản lý tin (stories) của người dùng
CREATE TABLE Stories (
    StoryID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của story
    UserID BIGINT FOREIGN KEY REFERENCES Users(UserID), -- Người đăng story
    MediaUrl VARCHAR(255), -- Đường dẫn media
    MediaType VARCHAR(20), -- Loại media
    Duration INT DEFAULT 15, -- Thời lượng hiển thị (giây)
    ViewCount INT DEFAULT 0, -- Số lượt xem
    BackgroundColor VARCHAR(20), -- Màu nền
    TextContent NVARCHAR(500), -- Nội dung văn bản
    FontStyle VARCHAR(50), -- Kiểu chữ
    CreatedAt DATETIME DEFAULT GETDATE(), -- Thời điểm tạo
    ExpiresAt DATETIME, -- Thời điểm hết hạn (24 giờ sau khi tạo)
    IsDeleted BIT DEFAULT 0, -- Đánh dấu đã xóa
    CONSTRAINT CHK_Story_MediaType CHECK (MediaType IN ('image', 'video', 'text')) -- Kiểm tra loại media hợp lệ
);
GO

-- Bảng StoryViews: Theo dõi lượt xem stories
CREATE TABLE StoryViews (
    ViewID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của lượt xem
    StoryID BIGINT FOREIGN KEY REFERENCES Stories(StoryID), -- Liên kết với story
    ViewerID BIGINT FOREIGN KEY REFERENCES Users(UserID), -- Người xem
    ViewedAt DATETIME DEFAULT GETDATE(), -- Thời điểm xem
    CONSTRAINT UQ_Story_View UNIQUE (StoryID, ViewerID) -- Đảm bảo mỗi người chỉ được tính 1 lượt xem
);
GO

-- Bảng Comments: Quản lý bình luận nâng cao với hỗ trợ media
CREATE TABLE Comments (
    CommentID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của bình luận
    PostID BIGINT FOREIGN KEY REFERENCES Posts(PostID), -- Liên kết với bài đăng
    UserID BIGINT FOREIGN KEY REFERENCES Users(UserID), -- Người bình luận
    ParentCommentID BIGINT FOREIGN KEY REFERENCES Comments(CommentID), -- Bình luận cha (nếu là reply)
    Content NVARCHAR(MAX), -- Nội dung bình luận
    MediaUrl VARCHAR(255), -- Đường dẫn media đính kèm
    MediaType VARCHAR(20), -- Loại media
    LikesCount INT DEFAULT 0, -- Số lượt thích
    RepliesCount INT DEFAULT 0, -- Số lượt trả lời
    CreatedAt DATETIME DEFAULT GETDATE(), -- Thời điểm tạo
    UpdatedAt DATETIME, -- Thời điểm cập nhật
    DeletedAt DATETIME, -- Thời điểm xóa
    IsEdited BIT DEFAULT 0, -- Đánh dấu đã chỉnh sửa
    IsDeleted BIT DEFAULT 0, -- Đánh dấu đã xóa
    CONSTRAINT CHK_Comment_MediaType CHECK (MediaType IN ('image', 'video', 'gif')) -- Kiểm tra loại media hợp lệ
);
GO

-- Bảng Conversations: Quản lý cuộc trò chuyện
CREATE TABLE Conversations (
    ConversationID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của cuộc trò chuyện
    Type VARCHAR(20) DEFAULT 'private', -- Loại cuộc trò chuyện
    Title NVARCHAR(255), -- Tiêu đề (cho nhóm chat)
    CreatedBy BIGINT FOREIGN KEY REFERENCES Users(UserID), -- Người tạo
    CreatedAt DATETIME DEFAULT GETDATE(), -- Thời điểm tạo
    UpdatedAt DATETIME DEFAULT GETDATE(), -- Thời điểm cập nhật
    LastMessageAt DATETIME, -- Thời điểm tin nhắn cuối
    IsActive BIT DEFAULT 1, -- Trạng thái hoạt động
    CONSTRAINT CHK_Conversation_Type CHECK (Type IN ('private', 'group')) -- Kiểm tra loại cuộc trò chuyện hợp lệ
);
GO

-- Bảng ConversationParticipants: Quản lý người tham gia cuộc trò chuyện
CREATE TABLE ConversationParticipants (
    ParticipantID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của người tham gia
    ConversationID BIGINT FOREIGN KEY REFERENCES Conversations(ConversationID), -- Liên kết với cuộc trò chuyện
    UserID BIGINT FOREIGN KEY REFERENCES Users(UserID), -- Liên kết với người dùng
    JoinedAt DATETIME DEFAULT GETDATE(), -- Thời điểm tham gia
    LeftAt DATETIME, -- Thời điểm rời đi
    Role VARCHAR(20) DEFAULT 'member', -- Vai trò trong cuộc trò chuyện
    LastReadMessageID BIGINT, -- ID tin nhắn cuối cùng đã đọc
    IsAdmin BIT DEFAULT 0, -- Có phải admin không
    IsMuted BIT DEFAULT 0, -- Có bị tắt thông báo không
    CONSTRAINT CHK_Participant_Role CHECK (Role IN ('member', 'admin', 'moderator')) -- Kiểm tra vai trò hợp lệ
);
GO

-- Bảng Messages: Quản lý tin nhắn trong cuộc trò chuyện
CREATE TABLE Messages (
    MessageID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của tin nhắn
    ConversationID BIGINT FOREIGN KEY REFERENCES Conversations(ConversationID), -- Liên kết với cuộc trò chuyện
    SenderID BIGINT FOREIGN KEY REFERENCES Users(UserID), -- Người gửi tin nhắn
    Type VARCHAR(20) DEFAULT 'text', -- Loại tin nhắn
    Content NVARCHAR(MAX), -- Nội dung tin nhắn
    MediaUrl VARCHAR(255), -- Đường dẫn media đính kèm
    MediaType VARCHAR(20), -- Loại media
    ReplyToMessageID BIGINT FOREIGN KEY REFERENCES Messages(MessageID), -- Tin nhắn được trả lời
    IsEdited BIT DEFAULT 0, -- Đã chỉnh sửa chưa
    IsDeleted BIT DEFAULT 0, -- Đã xóa chưa
    CreatedAt DATETIME DEFAULT GETDATE(), -- Thời điểm tạo
    UpdatedAt DATETIME, -- Thời điểm cập nhật
    DeletedAt DATETIME, -- Thời điểm xóa
    CONSTRAINT CHK_Message_Type CHECK (Type IN ('text', 'image', 'video', 'file', 'audio', 'location')) -- Kiểm tra loại tin nhắn hợp lệ
);
GO
-- Bảng Calls: Quản lý cuộc gọi video/audio
CREATE TABLE Calls (
    CallID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của cuộc gọi
    ConversationID BIGINT FOREIGN KEY REFERENCES Conversations(ConversationID), -- Liên kết với cuộc trò chuyện
    InitiatorID BIGINT FOREIGN KEY REFERENCES Users(UserID), -- Người bắt đầu cuộc gọi
    Type VARCHAR(20), -- Loại cuộc gọi
    StartTime DATETIME DEFAULT GETDATE(), -- Thời điểm bắt đầu
    EndTime DATETIME, -- Thời điểm kết thúc
    Status VARCHAR(20) DEFAULT 'initiated', -- Trạng thái cuộc gọi
    Duration INT, -- Thời lượng (giây)
    Quality VARCHAR(20), -- Chất lượng cuộc gọi
    RecordingUrl VARCHAR(255), -- Đường dẫn bản ghi
    CONSTRAINT CHK_Call_Type CHECK (Type IN ('audio', 'video')), -- Kiểm tra loại cuộc gọi hợp lệ
    CONSTRAINT CHK_Call_Status CHECK (Status IN ('initiated', 'ringing', 'ongoing', 'ended', 'missed', 'rejected')) -- Kiểm tra trạng thái hợp lệ
);
GO

-- Bảng CallParticipants: Quản lý người tham gia cuộc gọi
CREATE TABLE CallParticipants (
    CallParticipantID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của người tham gia
    CallID BIGINT FOREIGN KEY REFERENCES Calls(CallID), -- Liên kết với cuộc gọi
    UserID BIGINT FOREIGN KEY REFERENCES Users(UserID), -- Liên kết với người dùng
    JoinTime DATETIME, -- Thời điểm tham gia
    LeaveTime DATETIME, -- Thời điểm rời đi
    Status VARCHAR(20), -- Trạng thái tham gia
    DeviceInfo NVARCHAR(255), -- Thông tin thiết bị
    NetworkQuality VARCHAR(20), -- Chất lượng mạng
    CONSTRAINT CHK_CallParticipant_Status CHECK (Status IN ('invited', 'joined', 'left', 'declined')) -- Kiểm tra trạng thái hợp lệ
);
GO

-- Bảng MessageStatus: Theo dõi trạng thái tin nhắn
CREATE TABLE MessageStatus (
    StatusID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của trạng thái
    MessageID BIGINT FOREIGN KEY REFERENCES Messages(MessageID), -- Liên kết với tin nhắn
    UserID BIGINT FOREIGN KEY REFERENCES Users(UserID), -- Liên kết với người dùng
    Status VARCHAR(20) DEFAULT 'sent', -- Trạng thái tin nhắn
    UpdatedAt DATETIME DEFAULT GETDATE(), -- Thời điểm cập nhật
    CONSTRAINT CHK_Message_Status CHECK (Status IN ('sent', 'delivered', 'read')), -- Kiểm tra trạng thái hợp lệ
    CONSTRAINT UQ_Message_User_Status UNIQUE (MessageID, UserID) -- Đảm bảo không trùng lặp
);
GO
-- Bảng UserPresence: Theo dõi trạng thái hoạt động của người dùng
CREATE TABLE UserPresence (
    PresenceID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của trạng thái
    UserID BIGINT FOREIGN KEY REFERENCES Users(UserID), -- Liên kết với người dùng
    Status VARCHAR(20) DEFAULT 'offline', -- Trạng thái hoạt động
    LastActiveAt DATETIME DEFAULT GETDATE(), -- Thời điểm hoạt động cuối
    CurrentDeviceID VARCHAR(255), -- ID thiết bị đang sử dụng
    LastLocation NVARCHAR(MAX), -- Vị trí cuối cùng (định dạng JSON)
    CONSTRAINT CHK_Presence_Status CHECK (Status IN ('online', 'offline', 'away', 'busy', 'in_call')) -- Kiểm tra trạng thái hợp lệ
);
GO

-- Bảng Notifications: Quản lý thông báo
CREATE TABLE Notifications (
    NotificationID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của thông báo
    UserID BIGINT FOREIGN KEY REFERENCES Users(UserID), -- Người nhận thông báo
    Type VARCHAR(50), -- Loại thông báo
    Title NVARCHAR(255), -- Tiêu đề thông báo
    Content NVARCHAR(MAX), -- Nội dung thông báo
    RelatedID BIGINT, -- ID tham chiếu liên quan
    RelatedType VARCHAR(50), -- Tên bảng tham chiếu
    IsRead BIT DEFAULT 0, -- Đã đọc chưa
    CreatedAt DATETIME DEFAULT GETDATE(), -- Thời điểm tạo
    ExpiresAt DATETIME, -- Thời điểm hết hạn
    Priority VARCHAR(20) DEFAULT 'normal', -- Độ ưu tiên
    CONSTRAINT CHK_Notification_Type CHECK (Type IN ( -- Kiểm tra loại thông báo hợp lệ
        'message', 'call', 'missed_call', 'comment', 
        'reply', 'story_view', 'mention', 'reaction'
    ))
);

GO
-- Tạo các chỉ mục (Index) cho tính năng real-time
CREATE INDEX IX_Messages_ConversationID ON Messages(ConversationID); -- Tối ưu truy vấn tin nhắn theo cuộc trò chuyện
CREATE INDEX IX_Messages_CreatedAt ON Messages(CreatedAt DESC); -- Tối ưu sắp xếp tin nhắn theo thời gian
CREATE INDEX IX_Calls_Status ON Calls(Status); -- Tối ưu truy vấn cuộc gọi theo trạng thái
CREATE INDEX IX_UserPresence_Status ON UserPresence(Status); -- Tối ưu truy vấn trạng thái người dùng
CREATE INDEX IX_Notifications_UserID_IsRead ON Notifications(UserID, IsRead); -- Tối ưu truy vấn thông báo chưa đọc
CREATE INDEX IX_Stories_ExpiresAt ON Stories(ExpiresAt); -- Tối ưu truy vấn story hết hạn
CREATE INDEX IX_ConversationParticipants_UserID ON ConversationParticipants(UserID); -- Tối ưu truy vấn cuộc trò chuyện của người dùng
GO

-- Bảng NotificationTemplates: Quản lý mẫu thông báo
CREATE TABLE NotificationTemplates (
    TemplateID INT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của mẫu
    Type VARCHAR(50), -- Loại mẫu thông báo
    Title NVARCHAR(255), -- Tiêu đề mẫu
    Content NVARCHAR(MAX), -- Nội dung mẫu
    Parameters NVARCHAR(MAX), -- Tham số (định dạng JSON)
    CreatedAt DATETIME DEFAULT GETDATE() -- Thời điểm tạo
);
GO
-- Bảng NotificationDelivery: Theo dõi việc gửi thông báo
CREATE TABLE NotificationDelivery (
    DeliveryID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của lần gửi
    NotificationID BIGINT FOREIGN KEY REFERENCES Notifications(NotificationID), -- Liên kết với thông báo
    Channel VARCHAR(20), -- Kênh gửi thông báo
    Status VARCHAR(20), -- Trạng thái gửi
    SentAt DATETIME, -- Thời điểm gửi
    DeliveredAt DATETIME, -- Thời điểm nhận
    ErrorMessage NVARCHAR(MAX), -- Thông báo lỗi nếu có
    CONSTRAINT CHK_Delivery_Channel CHECK (Channel IN ('email', 'push', 'sms', 'in-app')), -- Kiểm tra kênh gửi hợp lệ
    CONSTRAINT CHK_Delivery_Status CHECK (Status IN ('pending', 'sent', 'delivered', 'failed')) -- Kiểm tra trạng thái hợp lệ
);
GO
-- Bảng CacheEntries: Lưu trữ cache
CREATE TABLE CacheEntries (
    CacheKey VARCHAR(255) PRIMARY KEY, -- Khóa cache
    Value NVARCHAR(MAX), -- Giá trị cache
    ExpiresAt DATETIME, -- Thời điểm hết hạn
    CreatedAt DATETIME DEFAULT GETDATE(), -- Thời điểm tạo
    UpdatedAt DATETIME DEFAULT GETDATE() -- Thời điểm cập nhật
);
GO
-- Bảng Events: Quản lý sự kiện
CREATE TABLE Events (
    EventID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của sự kiện
    Title NVARCHAR(255) NOT NULL, -- Tên sự kiện
    Description NVARCHAR(MAX), -- Mô tả chi tiết
    Category VARCHAR(50), -- Loại sự kiện
    EventDate DATE NOT NULL, -- Ngày diễn ra
    EventTime TIME NOT NULL, -- Thời gian bắt đầu
    Location NVARCHAR(255), -- Địa điểm
    ImageUrl VARCHAR(500), -- Ảnh bìa sự kiện
    MaxAttendees INT, -- Số lượng người tham gia tối đa
    CurrentAttendees INT DEFAULT 0, -- Số người đã đăng ký
    Price DECIMAL(10,2), -- Giá vé
    Organizer NVARCHAR(255), -- Đơn vị tổ chức
    Difficulty VARCHAR(20), -- Mức độ khó
    Status VARCHAR(20) DEFAULT 'upcoming', -- Trạng thái sự kiện
    CreatedBy BIGINT FOREIGN KEY REFERENCES Users(UserID), -- Người tạo
    CreatedAt DATETIME DEFAULT GETDATE(), -- Thời điểm tạo
    UpdatedAt DATETIME, -- Thời điểm cập nhật
    DeletedAt DATETIME, -- Thời điểm xóa
    CONSTRAINT CHK_Event_Status CHECK (Status IN ('upcoming', 'ongoing', 'completed', 'cancelled')), -- Kiểm tra trạng thái hợp lệ
    CONSTRAINT CHK_Event_Difficulty CHECK (Difficulty IN ('beginner', 'intermediate', 'advanced', 'expert')), -- Kiểm tra độ khó hợp lệ
    CONSTRAINT CHK_Event_Category CHECK (Category IN ( -- Kiểm tra loại sự kiện hợp lệ
        'Competitive Programming', 'Hackathon', 'Web Development', 
        'AI/ML', 'Mobile Development', 'DevOps', 'Security'
    ))
);

GO

-- Chi tiết giải thưởng
CREATE TABLE EventPrizes (
    PrizeID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của giải thưởng
    EventID BIGINT FOREIGN KEY REFERENCES Events(EventID), -- Liên kết với sự kiện
    Rank INT, -- Thứ hạng
    PrizeAmount DECIMAL(10,2), -- Giá trị giải thưởng
    Description NVARCHAR(500), -- Mô tả giải thưởng
    CONSTRAINT CHK_Prize_Rank CHECK (Rank > 0) -- Kiểm tra thứ hạng hợp lệ
);
GO

-- Ngôn ngữ lập trình được sử dụng trong sự kiện
CREATE TABLE EventProgrammingLanguages (
    EventID BIGINT FOREIGN KEY REFERENCES Events(EventID), -- Liên kết với sự kiện
    Language VARCHAR(50), -- Tên ngôn ngữ lập trình
    PRIMARY KEY (EventID, Language) -- Khóa chính kết hợp
);
GO

-- Công nghệ sử dụng trong sự kiện
CREATE TABLE EventTechnologies (
    EventID BIGINT FOREIGN KEY REFERENCES Events(EventID), -- Liên kết với sự kiện
    Technology VARCHAR(100), -- Tên công nghệ
    PRIMARY KEY (EventID, Technology) -- Khóa chính kết hợp
);
GO
-- Vòng thi/Tracks của sự kiện
CREATE TABLE EventRounds (
    RoundID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của vòng thi
    EventID BIGINT FOREIGN KEY REFERENCES Events(EventID), -- Liên kết với sự kiện
    Name NVARCHAR(255), -- Tên vòng thi
    Duration INT, -- Thời lượng (phút)
    Problems INT, -- Số lượng bài tập
    Description NVARCHAR(MAX), -- Mô tả vòng thi
    StartTime DATETIME, -- Thời gian bắt đầu
    EndTime DATETIME -- Thời gian kết thúc
);
GO
-- Người tham gia sự kiện
CREATE TABLE EventParticipants (
    ParticipantID BIGINT IDENTITY(1,1) PRIMARY KEY,
    EventID BIGINT FOREIGN KEY REFERENCES Events(EventID),
    UserID BIGINT FOREIGN KEY REFERENCES Users(UserID),
    RegistrationDate DATETIME DEFAULT GETDATE(),
    Status VARCHAR(20) DEFAULT 'registered', -- Trạng thái đăng ký
    TeamName NVARCHAR(100), -- Tên nhóm (nếu có)
    PaymentStatus VARCHAR(20), -- Trạng thái thanh toán
    AttendanceStatus VARCHAR(20), -- Trạng thái tham gia
    CONSTRAINT CHK_Participant_Status CHECK (Status IN ('registered', 'confirmed', 'cancelled', 'attended')),
    CONSTRAINT CHK_Payment_Status CHECK (PaymentStatus IN ('pending', 'completed', 'refunded', 'free')),
    CONSTRAINT CHK_Attendance_Status CHECK (AttendanceStatus IN ('pending', 'present', 'absent')),
    CONSTRAINT UQ_Event_User UNIQUE (EventID, UserID)
);
GO
-- Lịch trình sự kiện
CREATE TABLE EventSchedule (
    ScheduleID BIGINT IDENTITY(1,1) PRIMARY KEY,
    EventID BIGINT FOREIGN KEY REFERENCES Events(EventID),
    ActivityName NVARCHAR(255), -- Tên hoạt động
    StartTime DATETIME, -- Thời gian bắt đầu
    EndTime DATETIME, -- Thời gian kết thúc
    Description NVARCHAR(MAX), -- Mô tả hoạt động
    Location NVARCHAR(255), -- Địa điểm cụ thể
    Type VARCHAR(50), -- Loại hoạt động
    CONSTRAINT CHK_Schedule_Type CHECK (Type IN (
        'registration', 'opening', 'main_event', 
        'break', 'networking', 'closing'
    ))
);
GO
-- Indexes
CREATE INDEX IX_Events_Date ON Events(EventDate); -- Index cho cột EventDate trong bảng Events để tối ưu tìm kiếm theo ngày
CREATE INDEX IX_Events_Category ON Events(Category); -- Index cho cột Category để tối ưu tìm kiếm theo danh mục sự kiện
CREATE INDEX IX_Events_Status ON Events(Status); -- Index cho cột Status để tối ưu tìm kiếm theo trạng thái sự kiện
CREATE INDEX IX_EventParticipants_EventID ON EventParticipants(EventID); -- Index cho cột EventID trong bảng EventParticipants để tối ưu join với bảng Events
CREATE INDEX IX_EventSchedule_EventID ON EventSchedule(EventID); -- Index cho cột EventID trong bảng EventSchedule để tối ưu join với bảng Events
GO
-- Bảng thành tích sự kiện
CREATE TABLE EventAchievements (
    AchievementID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của thành tích
    EventID BIGINT FOREIGN KEY REFERENCES Events(EventID), -- Liên kết với sự kiện
    UserID BIGINT FOREIGN KEY REFERENCES Users(UserID), -- Liên kết với người dùng đạt thành tích
    Position INT, -- Thứ hạng đạt được trong sự kiện
    Points INT, -- Điểm thưởng cho thành tích
    BadgeType VARCHAR(50), -- Loại huy hiệu đạt được
    AwardedAt DATETIME DEFAULT GETDATE(), -- Thời điểm đạt thành tích
    CONSTRAINT CHK_Badge_Type CHECK (BadgeType IN ( -- Kiểm tra loại huy hiệu hợp lệ
        'GOLD_MEDAL', 'SILVER_MEDAL', 'BRONZE_MEDAL',
        'FIRST_PLACE', 'TOP_3', 'TOP_10',
        'PERFECT_SCORE', 'FAST_SOLVER', 'TEAM_WINNER'
    ))
);
GO

-- Bảng thành tích khóa học
CREATE TABLE CourseAchievements (
    AchievementID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của thành tích
    CourseID BIGINT FOREIGN KEY REFERENCES Courses(CourseID), -- Liên kết với khóa học
    UserID BIGINT FOREIGN KEY REFERENCES Users(UserID), -- Liên kết với người dùng đạt thành tích
    CompletionTime INT, -- Thời gian hoàn thành khóa học (phút)
    CorrectAnswers INT, -- Số câu trả lời đúng
    TotalQuestions INT, -- Tổng số câu hỏi
    Score DECIMAL(5,2), -- Điểm số đạt được
    BadgeType VARCHAR(50), -- Loại huy hiệu đạt được
    AwardedAt DATETIME DEFAULT GETDATE(), -- Thời điểm đạt thành tích
    CONSTRAINT CHK_Course_Badge CHECK (BadgeType IN ( -- Kiểm tra loại huy hiệu hợp lệ
        'COURSE_MASTER', 'QUICK_LEARNER', 'PERFECT_SCORE',
        'FIRST_COMPLETER', 'TOP_PERFORMER', 'CONSISTENT_LEARNER'
    ))
);
GO
-- Bảng lịch sử điểm ranking
CREATE TABLE RankingHistory (
    HistoryID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của lịch sử
    UserID BIGINT FOREIGN KEY REFERENCES Users(UserID), -- Liên kết với người dùng
    Type VARCHAR(20), -- Loại hoạt động (EVENT hoặc COURSE)
    RelatedID BIGINT, -- ID của sự kiện hoặc khóa học liên quan
    PointsEarned INT, -- Số điểm đạt được
    Reason NVARCHAR(255), -- Lý do được cộng điểm
    CreatedAt DATETIME DEFAULT GETDATE(), -- Thời điểm tạo bản ghi
    CONSTRAINT CHK_Ranking_Type CHECK (Type IN ('EVENT', 'COURSE')) -- Kiểm tra loại hoạt động hợp lệ
);
GO
-- Bảng thống kê theo thời gian
CREATE TABLE RankingStats (
    StatID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của thống kê
    UserID BIGINT FOREIGN KEY REFERENCES Users(UserID), -- Liên kết với người dùng
    PeriodType VARCHAR(20), -- Loại kỳ thống kê (WEEKLY, MONTHLY, ALL_TIME)
    StartDate DATE, -- Ngày bắt đầu kỳ thống kê
    EndDate DATE, -- Ngày kết thúc kỳ thống kê
    TotalPoints INT DEFAULT 0, -- Tổng điểm trong kỳ
    EventsParticipated INT DEFAULT 0, -- Số sự kiện đã tham gia
    CoursesCompleted INT DEFAULT 0, -- Số khóa học đã hoàn thành
    AverageAccuracy DECIMAL(5,2), -- Độ chính xác trung bình
    CONSTRAINT CHK_Period_Type CHECK (PeriodType IN ('WEEKLY', 'MONTHLY', 'ALL_TIME')) -- Kiểm tra loại kỳ thống kê hợp lệ
);
GO
-- Indexes for Ranking System
CREATE INDEX IX_UserRankings_TotalPoints ON UserRankings(TotalPoints DESC); -- Index cho cột TotalPoints để tối ưu sắp xếp theo điểm
CREATE INDEX IX_UserRankings_Tier ON UserRankings(Tier); -- Index cho cột Tier để tối ưu tìm kiếm theo hạng
CREATE INDEX IX_EventAchievements_UserID ON EventAchievements(UserID); -- Index cho cột UserID trong bảng EventAchievements để tối ưu join
CREATE INDEX IX_CourseAchievements_UserID ON CourseAchievements(UserID); -- Index cho cột UserID trong bảng CourseAchievements để tối ưu join
CREATE INDEX IX_RankingHistory_UserID ON RankingHistory(UserID); -- Index cho cột UserID trong bảng RankingHistory để tối ưu join
CREATE INDEX IX_RankingStats_UserID_PeriodType ON RankingStats(UserID, PeriodType); -- Index kết hợp cho UserID và PeriodType để tối ưu tìm kiếm thống kê

-- Trigger để cập nhật điểm ranking khi có thành tích mới
GO

CREATE TRIGGER TR_UpdateRankingPoints
ON RankingHistory
AFTER INSERT -- Kích hoạt sau khi thêm bản ghi mới vào RankingHistory
AS
BEGIN
    UPDATE ur -- Cập nhật bảng UserRankings
    SET TotalPoints = ur.TotalPoints + i.PointsEarned, -- Cộng điểm mới vào tổng điểm
        LastCalculatedAt = GETDATE() -- Cập nhật thời điểm tính toán
    FROM UserRankings ur
    INNER JOIN inserted i ON ur.UserID = i.UserID; -- Join với bản ghi mới thêm vào
END;

-- Trigger để tự động tính toán và cập nhật tier
GO
CREATE TRIGGER TR_UpdateUserTier
ON UserRankings
AFTER UPDATE -- Kích hoạt sau khi cập nhật bảng UserRankings
AS
BEGIN
    IF UPDATE(TotalPoints) -- Chỉ thực hiện khi TotalPoints bị thay đổi
    BEGIN
        UPDATE ur
        SET Tier = -- Cập nhật tier dựa trên tổng điểm
            CASE
                WHEN TotalPoints >= 10000 THEN 'MASTER'
                WHEN TotalPoints >= 5000 THEN 'DIAMOND'
                WHEN TotalPoints >= 2500 THEN 'PLATINUM'
                WHEN TotalPoints >= 1000 THEN 'GOLD'
                WHEN TotalPoints >= 500 THEN 'SILVER'
                ELSE 'BRONZE'
            END
        FROM UserRankings ur
        INNER JOIN inserted i ON ur.RankingID = i.RankingID; -- Join với bản ghi bị cập nhật
    END;
END;
GO

-- Bảng lưu trữ file mẫu đáp án
CREATE TABLE ExamAnswerTemplates (
    TemplateID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của template
    ExamID BIGINT FOREIGN KEY REFERENCES Exams(ExamID), -- Liên kết với bài thi
    Content NVARCHAR(MAX), -- Nội dung file word mẫu
    Keywords NVARCHAR(MAX), -- Từ khóa quan trọng dạng JSON
    MinimumMatchPercentage DECIMAL(5,2), -- Tỷ lệ khớp tối thiểu yêu cầu
    CreatedBy BIGINT FOREIGN KEY REFERENCES Users(UserID), -- Người tạo template
    CreatedAt DATETIME DEFAULT GETDATE(), -- Thời điểm tạo
    UpdatedAt DATETIME -- Thời điểm cập nhật
);
GO

-- Bảng phân tích câu trả lời essay
CREATE TABLE EssayAnswerAnalysis (
    AnalysisID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự tăng của phân tích
    AnswerID BIGINT FOREIGN KEY REFERENCES ExamAnswers(AnswerID), -- Liên kết với câu trả lời
    MatchPercentage DECIMAL(5,2), -- Tỷ lệ khớp với mẫu
    KeywordsMatched INT, -- Số từ khóa khớp được tìm thấy
    TotalKeywords INT, -- Tổng số từ khóa cần tìm
    ContentSimilarity DECIMAL(5,2), -- Độ tương đồng nội dung
    GrammarScore DECIMAL(5,2), -- Điểm ngữ pháp
    AnalyzedAt DATETIME DEFAULT GETDATE(), -- Thời điểm phân tích
    AutoGradedScore INT, -- Điểm được chấm tự động
    FinalScore INT, -- Điểm sau khi giáo viên review
    ReviewerComments NVARCHAR(MAX) -- Nhận xét của người review
);
GO

-- Trigger tự động phân tích và chấm điểm bài essay
CREATE TRIGGER TR_AnalyzeEssayAnswer
ON ExamAnswers
AFTER INSERT -- Kích hoạt sau khi thêm câu trả lời mới
AS
BEGIN
    DECLARE @AnswerID BIGINT -- Khai báo biến lưu ID câu trả lời
    DECLARE @ExamID BIGINT -- Khai báo biến lưu ID bài thi
    DECLARE @Answer NVARCHAR(MAX) -- Khai báo biến lưu nội dung câu trả lời
    DECLARE @TemplateContent NVARCHAR(MAX) -- Khai báo biến lưu nội dung template
    DECLARE @Keywords NVARCHAR(MAX) -- Khai báo biến lưu từ khóa
    DECLARE @MinMatch DECIMAL(5,2) -- Khai báo biến lưu tỷ lệ khớp tối thiểu

    -- Lấy thông tin bài làm mới
    SELECT 
        @AnswerID = i.AnswerID,
        @Answer = i.Answer,
        @ExamID = eq.ExamID
    FROM inserted i
    JOIN ExamQuestions eq ON i.QuestionID = eq.QuestionID;

    -- Lấy thông tin template
    SELECT 
        @TemplateContent = Content,
        @Keywords = Keywords,
        @MinMatch = MinimumMatchPercentage
    FROM ExamAnswerTemplates
    WHERE ExamID = @ExamID;

    -- Tính toán độ tương đồng và chấm điểm
    INSERT INTO EssayAnswerAnalysis (
        AnswerID,
        MatchPercentage,
        KeywordsMatched,
        TotalKeywords,
        ContentSimilarity,
        GrammarScore,
        AutoGradedScore
    )
    VALUES (
        @AnswerID,
        dbo.CalculateMatchPercentage(@Answer, @TemplateContent), -- Gọi hàm tính % khớp
        dbo.CountMatchedKeywords(@Answer, @Keywords), -- Gọi hàm đếm từ khóa khớp
        JSON_QUERY(@Keywords).length, -- Đếm tổng số từ khóa
        dbo.CalculateContentSimilarity(@Answer, @TemplateContent), -- Gọi hàm tính độ tương đồng
        dbo.CalculateGrammarScore(@Answer), -- Gọi hàm chấm điểm ngữ pháp
        dbo.CalculateAutoScore(@Answer, @TemplateContent, @Keywords, @MinMatch) -- Gọi hàm tính điểm tự động
    );

    -- Cập nhật điểm vào bảng ExamAnswers
    UPDATE ExamAnswers
    SET Score = (
        SELECT AutoGradedScore 
        FROM EssayAnswerAnalysis 
        WHERE AnswerID = @AnswerID
    )
    WHERE AnswerID = @AnswerID;
END;
GO

-- Trigger cập nhật điểm tổng sau khi chấm xong essay
CREATE TRIGGER TR_UpdateExamScoreAfterEssay
ON EssayAnswerAnalysis
AFTER INSERT -- Kích hoạt sau khi thêm phân tích mới
AS
BEGIN
    DECLARE @ParticipantID BIGINT -- Khai báo biến lưu ID người tham gia
    DECLARE @TotalScore INT -- Khai báo biến lưu điểm tổng
    DECLARE @UserID BIGINT -- Khai báo biến lưu ID người dùng
    DECLARE @ExamID BIGINT -- Khai báo biến lưu ID bài thi

    -- Lấy thông tin bài thi
    SELECT 
        @ParticipantID = ep.ParticipantID,
        @UserID = ep.UserID,
        @ExamID = ep.ExamID
    FROM inserted i
    JOIN ExamAnswers ea ON i.AnswerID = ea.AnswerID
    JOIN ExamParticipants ep ON ea.ParticipantID = ep.ParticipantID;

    -- Tính điểm tổng
    SELECT @TotalScore = AVG(eaa.AutoGradedScore)
    FROM EssayAnswerAnalysis eaa
    JOIN ExamAnswers ea ON eaa.AnswerID = ea.AnswerID
    WHERE ea.ParticipantID = @ParticipantID;

    -- Cập nhật điểm tổng
    UPDATE ExamParticipants
    SET 
        Score = @TotalScore,
        Status = 'completed',
        CompletedAt = GETDATE()
    WHERE ParticipantID = @ParticipantID;

    -- Thêm thành tích và cập nhật ranking
    INSERT INTO RankingHistory (
        UserID,
        Type,
        RelatedID,
        PointsEarned,
        Reason
    )
    VALUES (
        @UserID,
        'EXAM',
        @ExamID,
        CASE -- Tính điểm thưởng dựa trên điểm tổng
            WHEN @TotalScore >= 90 THEN 100
            WHEN @TotalScore >= 80 THEN 80
            WHEN @TotalScore >= 70 THEN 60
            WHEN @TotalScore >= 60 THEN 40
            ELSE 20
        END,
        'Essay Exam Completion: ' + CAST(@TotalScore as VARCHAR(3)) + '/100'
    );
END;


----------------------------------------------Thêm Role, Permission cho User------------------------------------------

--Chạy lệnh này để lưu dữ liệu từ cột role trong bảng Users vào một bảng tạm (Backup_UserRoles):
SELECT UserID AS user_id, role INTO Backup_UserRoles FROM Users WHERE role IS NOT NULL;

--Tạo các bảng cần thiết cho chức năng quản lý quyền User
CREATE TABLE Roles (
    id INT PRIMARY KEY IDENTITY,
    roleName NVARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE Permissions (
    id INT PRIMARY KEY IDENTITY,
    permissionName NVARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE UserRole (
    user_id BIGINT,
    role_id INT,
    assignedAt DATETIME DEFAULT GETDATE(),
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES Users(UserID) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES Roles(id) ON DELETE CASCADE
);

CREATE TABLE RolePermission (
    role_id INT,
    permission_id INT,
    PRIMARY KEY (role_id, permission_id),
    FOREIGN KEY (role_id) REFERENCES Roles(id) ON DELETE CASCADE,
    FOREIGN KEY (permission_id) REFERENCES Permissions(id) ON DELETE CASCADE
);

--Chuyển dữ liệu backup role của Users vào bảng Roles:
INSERT INTO Roles (roleName)
SELECT DISTINCT role FROM Backup_UserRoles;

--Liên kết User với Role trong bảng UserRole:
INSERT INTO UserRole (user_id, role_id)
SELECT bu.user_id, r.id
FROM Backup_UserRoles bu
JOIN Roles r ON bu.role = r.roleName;

--Xem tên ràng buộc Default Constraint trong bảng Users của trường Role
SELECT name 
FROM sys.default_constraints 
WHERE parent_object_id = OBJECT_ID('Users') 
AND parent_column_id = COLUMNPROPERTY(OBJECT_ID('Users'), 'role', 'ColumnId');

--Xóa ràng buộc Role: Default Constraint (LƯU Ý: Default Constraint có thể khác nhau ở các máy, đây là tham số phải chạy câu lệnh ở trên để tìm)
ALTER TABLE Users DROP CONSTRAINT DF__Users__Role__07020F21;

--Xem tên ràng buộc Check Constraint của trường Role
SELECT name 
FROM sys.check_constraints 
WHERE parent_object_id = OBJECT_ID('Users') 
AND parent_column_id = COLUMNPROPERTY(OBJECT_ID('Users'), 'role', 'ColumnId');

--Xóa ràng buộc Check Constraint của trường Role (LƯU Ý: Check Constraint có thể khác nhau ở các máy, đây là tham số phải chạy câu lệnh ở trên để tìm)
ALTER TABLE Users DROP CONSTRAINT CHK_User_Role;

--Xóa cột role khỏi bảng Users
ALTER TABLE Users DROP COLUMN role;

--Xóa bảng Backup_UserRoles:
DROP TABLE Backup_UserRoles;
--------------------------------------Kết thúc Thêm Role, Permission cho User--------------------------------------------------------------


--------------------------------------Xóa trường TestCase trong bảng CodingExcercises------------------------------------------------------
ALTER TABLE CodingExercises DROP COLUMN TestCases


--------------------------------------Kết thúc Xóa trường TestCase trong bảng CodingExcercises--------------------------------------------------------------



-------------------------------------Thêm slug cho các bảng CourseModule, CoueseLesseon, CodingExercise---------------------------------------------------

ALTER TABLE [dbo].[CourseModules]
    ADD [Slug]  NVARCHAR(255) NULL
GO

-- Bước 1: Xoá index cũ (nếu tồn tại)
DROP INDEX IX_CourseModules_Slug_NotNull ON [dbo].[CourseModules];
GO

-- Bước 2: Tạo lại chỉ mục UNIQUE có điều kiện
CREATE UNIQUE INDEX IX_CourseModules_Slug_NotNull
ON [dbo].[CourseModules] ([Slug])
WHERE [Slug] IS NOT NULL;
GO


ALTER TABLE [dbo].[CourseLessons]
    ADD [Slug]  NVARCHAR(255) NULL
GO

-- Bước 1: Xoá index cũ (nếu tồn tại)
DROP INDEX IX_CourseLessons_Slug_NotNull ON [dbo].[CourseLessons];
GO

-- Bước 2: Tạo lại chỉ mục UNIQUE có điều kiện
CREATE UNIQUE INDEX IX_CourseLessons_Slug_NotNull
ON [dbo].[CourseLessons] ([Slug])
WHERE [Slug] IS NOT NULL;
GO



ALTER TABLE [dbo].[CodingExercises]
    ADD [Slug] NVARCHAR(255) NULL;
GO

DROP INDEX IF EXISTS IX_CodingExercises_Slug_NotNull ON [dbo].[CodingExercises];
GO

CREATE UNIQUE INDEX IX_CodingExercises_Slug_NotNull
ON [dbo].[CodingExercises] ([Slug])
WHERE [Slug] IS NOT NULL;
GO


-------------------------------------------Kết thúc----------------------------------------------------------------------------------------


-------------------------------------------Tạo Contest cho Lesson--------------------------------------------------------------------------
ALTER TABLE CourseLessons
ADD IsContest BIT DEFAULT 0,
    ContestStartTime DATETIME,
    ContestEndTime DATETIME,
    TotalPoints INT DEFAULT 0;

DROP TABLE ContestExerciseAttempts

CREATE TABLE ContestExerciseAttempts (
    AttemptID BIGINT IDENTITY(1,1) PRIMARY KEY,
    ExerciseID BIGINT NOT NULL, -- ID bài tập (coding hoặc essay)
    LessonID BIGINT NOT NULL FOREIGN KEY REFERENCES CourseLessons(LessonID),
    UserID BIGINT NOT NULL FOREIGN KEY REFERENCES Users(UserID),
    SubmittedAt DATETIME DEFAULT GETDATE(),
    Score INT,
    AttemptNumber INT,
    ExerciseType VARCHAR(20) NOT NULL CHECK (ExerciseType IN ('coding', 'essay')), -- Phân biệt loại bài
    UNIQUE(ExerciseID, UserID, ExerciseType) -- Đảm bảo mỗi user chỉ nộp 1 lần cho 1 bài loại cụ thể
);



-------------------------------------------Kết thúc Tạo Contest cho Lesson--------------------------------------------------------------------------



-------------------------------------------Thêm UserID cho bảng Lesson---------------------------------------------------------------------------
ALTER TABLE CourseLessons ADD CreatorID BIGINT;

ALTER TABLE CourseLessons
ADD CONSTRAINT fk_creator FOREIGN KEY (Creator) REFERENCES Users(UserID);

-------------------------------------------Kết thúc Thêm UserID cho bảng Lesson-------------------------------------------------------------------



-------------------------------------------Tạo bảng EssayExercises và EssaySubmissions để quản lý bài luyện tập------------------------------------
CREATE TABLE EssayExercises (
    ExerciseID BIGINT IDENTITY(1,1) PRIMARY KEY, -- ID tự động tăng
    LessonID BIGINT FOREIGN KEY REFERENCES CourseLessons(LessonID), -- Bài học liên quan
    Title NVARCHAR(255) NOT NULL, -- Tiêu đề bài tự luận
    Description NVARCHAR(MAX), -- Mô tả chi tiết đề bài
    SubjectName NVARCHAR(255), -- Tên môn học (Toán, Văn, Sử, CNTT...)
    ExpectedAnswer NVARCHAR(MAX), -- Nội dung đáp án mong đợi, để hỗ trợ chấm điểm tự động/AI
    TimeLimit INT DEFAULT NULL, -- Giới hạn thời gian (mili giây hoặc phút, tùy định nghĩa)
    Difficulty VARCHAR(20) DEFAULT 'medium', -- Mức độ khó
    Points INT DEFAULT 0, -- Điểm tối đa
    Slug NVARCHAR(255) NULL;
    CreatedAt DATETIME DEFAULT GETDATE(), -- Thời điểm tạo
    UpdatedAt DATETIME DEFAULT GETDATE(), -- Thời điểm cập nhật
    CONSTRAINT CHK_Essay_Exercise_Difficulty CHECK (Difficulty IN ('easy', 'medium', 'hard', 'expert'))
);

CREATE TABLE EssaySubmissions (
    SubmissionID BIGINT IDENTITY(1,1) PRIMARY KEY,
    ExerciseID BIGINT FOREIGN KEY REFERENCES EssayExercises(ExerciseID),
    UserID BIGINT FOREIGN KEY REFERENCES Users(UserID),
    AnswerText NVARCHAR(MAX), -- Bài làm của học viên
    SubmittedAt DATETIME DEFAULT GETDATE(),
    Score INT, -- Điểm chấm
    Feedback NVARCHAR(MAX) -- Nhận xét của giáo viên (nếu có)
);

-------------------------------------------Kết thúcTạo bảng EssayExercises và EssaySubmissions để quản lý bài luyện tập------------------------------------
